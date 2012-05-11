/**
 * Copyright (c) 2009-2012, Lukas Eder, lukas.eder@gmail.com
 *                             Christopher Deckers, chrriis@gmail.com
 * All rights reserved.
 *
 * This software is licensed to you under the Apache License, Version 2.0
 * (the "License"); You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * . Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * . Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * . Neither the name "jOOQ" nor the names of its contributors may be
 *   used to endorse or promote products derived from this software without
 *   specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.jooq.debug.console;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.jooq.debug.Breakpoint;
import org.jooq.debug.BreakpointAfterExecutionHit;
import org.jooq.debug.BreakpointBeforeExecutionHit;
import org.jooq.debug.BreakpointHitHandler;
import org.jooq.debug.Debugger;
import org.jooq.debug.console.misc.CheckBoxNode;
import org.jooq.debug.console.misc.CheckBoxNodeEditor;
import org.jooq.debug.console.misc.CheckBoxNodeRenderer;
import org.jooq.debug.console.misc.InvisibleSplitPane;

/**
 * @author Christopher Deckers
 */
@SuppressWarnings("serial")
public class DebuggerPane extends JPanel {

    private final ImageIcon BREAKPOINT_ON_ICON = new ImageIcon(getClass().getResource("/org/jooq/debug/console/resources/BreakpointOn16.png"));
    private final ImageIcon BREAKPOINT_OFF_ICON = new ImageIcon(getClass().getResource("/org/jooq/debug/console/resources/BreakpointOff16.png"));
    private final ImageIcon BREAKPOINT_HIT_ICON = new ImageIcon(getClass().getResource("/org/jooq/debug/console/resources/BreakpointHit16.png"));
    private final ImageIcon STACK_TRACE_ELEMENT_ICON = new ImageIcon(getClass().getResource("/org/jooq/debug/console/resources/StackTraceElement16.png"));

    private Debugger debugger;
    private DefaultMutableTreeNode rootNode;
    private JTree breakpointTree;
    private DefaultTreeModel breakpointTreeModel;
    private JPanel eastPane;

    public DebuggerPane(Debugger debugger) {
        super(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(2, 5, 5, 5));
        setOpaque(false);
        this.debugger = debugger;
        JPanel westPane = new JPanel(new BorderLayout());
        westPane.setOpaque(false);
        JPanel breakpointAddPane = new JPanel(new GridBagLayout());
        breakpointAddPane.setOpaque(false);
        breakpointAddPane.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        final JTextField addBreakpointTextField = new JTextField(7);
        breakpointAddPane.add(addBreakpointTextField, new GridBagConstraints(0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
        final JButton addBreakpointButton = new JButton("Add");
        addBreakpointButton.setOpaque(false);
        addBreakpointButton.setEnabled(false);
        breakpointAddPane.add(addBreakpointButton, new GridBagConstraints(1, 0, 1, 1, 0, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 2, 0, 0), 0, 0));
        addBreakpointTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e) {
                adjustStates();
            }
            @Override
            public void insertUpdate(DocumentEvent e) {
                adjustStates();
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                adjustStates();
            }
            private void adjustStates() {
                // TODO: restrict to unique names?
                boolean isEnabled = addBreakpointTextField.getText().length() > 0;
                addBreakpointButton.setEnabled(isEnabled);
            }
        });
        ActionListener addBreakpointActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = addBreakpointTextField.getText();
                if(name.length() == 0) {
                    return;
                }
                addBreakpointTextField.setText("");
                addBreakpoint(name);
            }
        };
        addBreakpointTextField.addActionListener(addBreakpointActionListener);
        addBreakpointButton.addActionListener(addBreakpointActionListener);
        westPane.add(breakpointAddPane, BorderLayout.NORTH);
        rootNode = new DefaultMutableTreeNode();
        breakpointTreeModel = new DefaultTreeModel(rootNode) {
            @Override
            public void valueForPathChanged(TreePath path, Object newValue) {
                if(newValue instanceof CheckBoxNode) {
                    CheckBoxNode node = (CheckBoxNode)path.getLastPathComponent();
                    node.setSelected(((CheckBoxNode) newValue).isSelected());
                    super.valueForPathChanged(path, node.getUserObject());
                    commitBreakpoints();
                }
            }
        };
        breakpointTree = new JTree(breakpointTreeModel);
        breakpointTree.setRootVisible(false);
        breakpointTree.setShowsRootHandles(true);
        breakpointTree.setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                Component c = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
                if(c instanceof JLabel) {
                    Icon icon = null;
                    if(value instanceof CheckBoxNode) {
                        icon = ((CheckBoxNode) value).isSelected()? BREAKPOINT_ON_ICON: BREAKPOINT_OFF_ICON;
                    } else if(value instanceof BreakpointBeforeExecutionHitNode) {
                        icon = BREAKPOINT_HIT_ICON;
                    } else if(value instanceof StackTraceElementNode) {
                        icon = STACK_TRACE_ELEMENT_ICON;
                    }
                    ((JLabel) c).setIcon(icon);
                }
                return c;
            }
        });
        // Order matters because we want the editor to have the old cell renderer as a base.
        breakpointTree.setCellEditor(new CheckBoxNodeEditor(breakpointTree));
        breakpointTree.setCellRenderer(new CheckBoxNodeRenderer(breakpointTree));
        breakpointTree.setEditable(true);
        JScrollPane breakpointTreeScrollPane = new JScrollPane(breakpointTree);
        breakpointTreeScrollPane.setPreferredSize(new Dimension(200, 200));
        westPane.add(breakpointTreeScrollPane, BorderLayout.CENTER);
        JPanel breakpointRemovePane = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        breakpointRemovePane.setOpaque(false);
        breakpointRemovePane.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
        final JButton removeBreakpointButton = new JButton("Remove");
        removeBreakpointButton.setOpaque(false);
        removeBreakpointButton.setEnabled(false);
        removeBreakpointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TreePath[] paths = breakpointTree.getSelectionPaths();
                boolean isValid = paths != null && paths.length > 0;
                if(isValid) {
                    for(int i=0; i<paths.length; i++) {
                        if(!(paths[i].getLastPathComponent() instanceof CheckBoxNode)) {
                            isValid = false;
                            break;
                        }
                    }
                }
                if(isValid) {
                    List<BreakpointBeforeExecutionHitNode> beforeExecutionHitNodeList = new ArrayList<DebuggerPane.BreakpointBeforeExecutionHitNode>();
                    // TODO: list of after exec
                    breakpointTree.cancelEditing();
                    for(int i=0; i<paths.length; i++) {
                        CheckBoxNode childNode = (CheckBoxNode)paths[i].getLastPathComponent();
                        int childCount = childNode.getChildCount();
                        for(int j=0; j<childCount; j++) {
                            TreeNode node = childNode.getChildAt(j);
                            if(node instanceof BreakpointBeforeExecutionHitNode) {
                                beforeExecutionHitNodeList.add((BreakpointBeforeExecutionHitNode)node);
                            } else {
                                // TODO: implement
                            }
                        }
                        int index = rootNode.getIndex(childNode);
                        rootNode.remove(index);
                        breakpointTreeModel.nodesWereRemoved(rootNode, new int[] {index}, new Object[] {childNode});
                    }
                    commitBreakpoints();
                    for(BreakpointBeforeExecutionHitNode node: beforeExecutionHitNodeList) {
                        synchronized (node) {
                            node.proceed();
                        }
                    }
                }
            }
        });
        breakpointTree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreePath[] paths = breakpointTree.getSelectionPaths();
                boolean isValid = paths != null && paths.length > 0;
                if(isValid) {
                    for(int i=0; i<paths.length; i++) {
                        if(!(paths[i].getLastPathComponent() instanceof CheckBoxNode)) {
                            isValid = false;
                            break;
                        }
                    }
                }
                removeBreakpointButton.setEnabled(isValid);
                processTreeSelection();
            }
        });
        breakpointRemovePane.add(removeBreakpointButton);
        westPane.add(breakpointRemovePane, BorderLayout.SOUTH);
        eastPane = new JPanel(new BorderLayout());
        eastPane.setOpaque(false);
        add(new InvisibleSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, westPane, eastPane), BorderLayout.CENTER);
    }

    private static int nextID = 1;

    private void addBreakpoint(String name) {
        breakpointTree.cancelEditing();
        Breakpoint breakpoint = new Breakpoint(nextID++, null, null, true, null, null, null);
        CheckBoxNode breakpointNode = new CheckBoxNode(breakpoint, name, true);
        rootNode.add(breakpointNode);
        breakpointTreeModel.nodesWereInserted(rootNode, new int[] {rootNode.getIndex(breakpointNode)});
        breakpointTree.expandPath(new TreePath(rootNode));
        commitBreakpoints();
        breakpointTree.setSelectionPath(new TreePath(new Object[] {rootNode, breakpointNode}));
    }

    void modifyBreakpoint(Breakpoint breakpoint) {
        int childCount = rootNode.getChildCount();
        for(int i=0; i<childCount; i++) {
            CheckBoxNode checkBoxNode = (CheckBoxNode)rootNode.getChildAt(i);
            Breakpoint breakpoint_ = (Breakpoint)checkBoxNode.getUserObject();
            if(breakpoint_.getID() == breakpoint.getID()) {
                checkBoxNode.setUserObject(breakpoint);
                commitBreakpoints();
                break;
            }
        }
    }

    private class StackTraceElementNode extends DefaultMutableTreeNode {
        private String name;
        public StackTraceElementNode(StackTraceElement stackTraceElement) {
            super(stackTraceElement);
            String className = stackTraceElement.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            name = className + '.' + stackTraceElement.getMethodName() + "()";
            if(stackTraceElement.getLineNumber() >= 0) {
                name += " line " + stackTraceElement.getLineNumber();
            }
        }
        @Override
        public String toString() {
            return name;
        }
    }

    class BreakpointBeforeExecutionHitNode extends DefaultMutableTreeNode {
        public BreakpointBeforeExecutionHitNode(BreakpointBeforeExecutionHit breakpointHit) {
            super(breakpointHit);
            StackTraceElement[] callerStackTraceElements = breakpointHit.getCallerStackTraceElements();
            for(StackTraceElement stackTraceElement: callerStackTraceElements) {
                add(new StackTraceElementNode(stackTraceElement));
            }
        }
        private boolean isLocked = true;
        public void proceed() {
            synchronized (this) {
                isLocked = false;
                notifyAll();
            }
        }
        public boolean isLocked() {
            synchronized (this) {
                return isLocked;
            }
        }
        @Override
        public String toString() {
            return "Thread [" + ((BreakpointBeforeExecutionHit)getUserObject()).getThreadName() + "]";
        }
    }

    void proceedBreakpointHit(BreakpointBeforeExecutionHitNode breakpointHitNode) {
        CheckBoxNode parentNode = (CheckBoxNode)breakpointHitNode.getParent();
        int index = parentNode.getIndex(breakpointHitNode);
        parentNode.remove(index);
        breakpointTreeModel.nodesWereRemoved(parentNode, new int[] {index}, new Object[] {breakpointHitNode});
        breakpointHitNode.proceed();
    }

    private BreakpointHitHandler breakpointHitHandler = new BreakpointHitHandler() {
        @Override
        public void processBreakpointBeforeExecutionHit(final BreakpointBeforeExecutionHit breakpointHit) {
            if(SwingUtilities.isEventDispatchThread()) {
                new IllegalStateException("Breakpoint triggered from UI thread: cannot break because the debugger needs a live UI thread!").printStackTrace();
            }
            final BreakpointBeforeExecutionHitNode node = new BreakpointBeforeExecutionHitNode(breakpointHit);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    int childCount = rootNode.getChildCount();
                    boolean isFound = false;
                    for(int i=0; i<childCount; i++) {
                        CheckBoxNode checkBoxNode = (CheckBoxNode)rootNode.getChildAt(i);
                        Breakpoint breakpoint = (Breakpoint)checkBoxNode.getUserObject();
                        if(breakpoint.getID() == breakpointHit.getBreakpointID()) {
                            int index = checkBoxNode.getChildCount();
                            checkBoxNode.add(node);
                            breakpointTreeModel.nodesWereInserted(checkBoxNode, new int[] {index});
                            breakpointTreeModel.nodeChanged(checkBoxNode);
                            breakpointTree.expandPath(new TreePath(new Object[] {rootNode, checkBoxNode}));
                            breakpointTree.repaint();
                            isFound = true;
                            break;
                        }
                    }
                    if(!isFound) {
                        node.proceed();
                    }
                }
            });
            synchronized (node) {
                while(node.isLocked()) {
                    try {
                        node.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        @Override
        public void processBreakpointAfterExecutionHit(BreakpointAfterExecutionHit breakpointHit) {
        }
    };

    private void commitBreakpoints() {
        List<Breakpoint> breakpointList = new ArrayList<Breakpoint>();
        int childCount = rootNode.getChildCount();
        for(int i=0; i<childCount; i++) {
            CheckBoxNode node = (CheckBoxNode)rootNode.getChildAt(i);
            if(node.isSelected()) {
                breakpointList.add((Breakpoint)node.getUserObject());
            }
        }
        Breakpoint[] breakpoints = breakpointList.toArray(new Breakpoint[0]);
        if(breakpoints.length == 0) {
            debugger.setBreakpointHitHandler(null);
        } else {
            debugger.setBreakpointHitHandler(breakpointHitHandler);
        }
        debugger.setBreakpoints(breakpoints);
    }

    private void processTreeSelection() {
        TreePath[] paths = breakpointTree.getSelectionPaths();
        // TODO: commit last value.
        eastPane.removeAll();
        if(paths != null && paths.length == 1) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)paths[0].getLastPathComponent();
            if(node instanceof CheckBoxNode) {
                Object o = node.getUserObject();
                eastPane.add(new BreakpointEditor(this, (Breakpoint)o));
            } else if(node instanceof BreakpointBeforeExecutionHitNode) {
                eastPane.add(new BreakpointHitEditor(this, (BreakpointBeforeExecutionHitNode)node));
            }
        }
        eastPane.revalidate();
        eastPane.repaint();
    }

}

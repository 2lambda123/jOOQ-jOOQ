/**
 * Copyright (c) 2009-2014, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 *
 * This work is dual-licensed
 * - under the Apache Software License 2.0 (the "ASL")
 * - under the jOOQ License and Maintenance Agreement (the "jOOQ License")
 * =============================================================================
 * You may choose which license applies to you:
 *
 * - If you're using this work with Open Source databases, you may choose
 *   either ASL or jOOQ License.
 * - If you're using this work with at least one commercial database, you must
 *   choose jOOQ License
 *
 * For more information, please visit http://www.jooq.org/licenses
 *
 * Apache Software License 2.0:
 * -----------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * jOOQ License and Maintenance Agreement:
 * -----------------------------------------------------------------------------
 * Data Geekery grants the Customer the non-exclusive, timely limited and
 * non-transferable license to install and use the Software under the terms of
 * the jOOQ License and Maintenance Agreement.
 *
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ License
 * and Maintenance Agreement for more details: http://www.jooq.org/licensing
 */
package org.jooq.oss

import java.io.File
import java.util.ArrayList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.regex.Pattern
import org.apache.commons.lang3.tuple.ImmutablePair
import org.jooq.SQLDialect
import org.jooq.xtend.Generators

import static java.util.regex.Pattern.*

// Use this to generate the jOOQ Open Source Edition code
class RemoveProCode {
    def static void main(String[] args) {
        Splitter::split("pro", "/../workspace-jooq-oss");
    }
}

// Use this to generate the jOOQ Professional and Enterprise Edition code
class RemoveTrialCode {
    def static void main(String[] args) {
        Splitter::split("trial", "/../workspace-jooq-pro");
    }
}

class Splitter extends Generators {
    
    static ExecutorService ex;
    static AtomicInteger charsTotal = new AtomicInteger(0);
    static AtomicInteger charsMasked = new AtomicInteger(0);
    String token;

    def static void split(String token, String workspace) {
        ex = Executors::newFixedThreadPool(4);
        
        val splitter = new Splitter(token);
        
        val workspaceIn = new File("../..").canonicalFile;
        val workspaceOut = new File(workspaceIn.canonicalPath + workspace).canonicalFile;
        
        for (project : workspaceIn.listFiles[f | f.name.startsWith("jOOQ")]) {
            val in = new File(workspaceIn, project.name);
            val out = new File(workspaceOut, project.name);
            splitter.transform(in, out, in);
        }
        
        ex.shutdown();
//        ex.awaitTermination(1, TimeUnit::MINUTES);
//
//        System::out.println();
//        System::out.println("Total  chars : " + charsTotal);
//        System::out.println("Masked chars : " + charsMasked);
//        System::out.println("Percentage   : " + (100.0 * charsMasked.get / charsTotal.get));
    }

    def void transform(File inRoot, File outRoot, File in) {
        val out = new File(outRoot.canonicalPath + "/" + in.canonicalPath.replace(inRoot.canonicalPath, ""));
        
        if (in.directory) {
            val files = in.listFiles[path |
                   !path.canonicalPath.endsWith(".class")
                && !path.canonicalPath.endsWith(".dat")
                && !path.canonicalPath.endsWith(".git")
                && !path.canonicalPath.endsWith(".jar")
                && !path.canonicalPath.endsWith(".pdf")
                && !path.canonicalPath.endsWith(".zip")
                && !path.canonicalPath.endsWith("._trace")
                && !path.canonicalPath.contains("jOOQ-test")
                && !path.canonicalPath.contains("jOOQ-tools")
                && !path.canonicalPath.contains("jOOQ-website")
                && !path.canonicalPath.contains("jOOQ-websites")
                && !path.canonicalPath.contains("jOOQ-webservices")
                && !path.canonicalPath.contains("jOOQ-parse")
                && !path.canonicalPath.contains("\\target\\")
                && !path.canonicalPath.contains("\\bin\\")
                
                // Activate this when we change anything to the Sakila db
                && !path.canonicalPath.contains("\\Sakila\\")
                
                && (token.equals("trial") || (
                       !path.canonicalPath.contains("\\access\\")
                    && !path.canonicalPath.contains("\\ase\\")
                    && !path.canonicalPath.contains("\\db2\\")
                    && !path.canonicalPath.contains("\\informix\\")
                    && !path.canonicalPath.contains("\\ingres\\")
                    && !path.canonicalPath.contains("\\jdbcoracle\\")
                    && !path.canonicalPath.contains("\\oracle\\")
                    && !path.canonicalPath.contains("\\oracle2\\")
                    && !path.canonicalPath.contains("\\oracle3\\")
                    && !path.canonicalPath.contains("\\sqlserver\\")
                    && !path.canonicalPath.contains("\\sybase\\")
                ))
            ];

            for (file : files) {
                transform(inRoot, outRoot, file);
            }
        }
        else if (token == "pro" && in.name.equals("LICENSE.txt")) {
            ex.submit[ |
                write(out, '''
Copyright (c) 2009-2014, Data Geekery GmbH (http://www.datageekery.com)
All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Other licenses:
-----------------------------------------------------------------------------
Commercial licenses for this work are available. These replace the above
ASL 2.0 and offer limited warranties, support, maintenance, and commercial
database integrations.

For more information, please visit: http://www.jooq.org/licenses''');
            ];
        }
        else {
            ex.submit[ |
                var original = read(in);
                var content = original;
    
                for (pattern : translateAll) {
                    val m = pattern.matcher(content);
                    
                    while (m.find) {
                        content = content.substring(0, m.start)
                                + m.group(1)
                                + m.group(2).replaceAll("\\S", "x")
                                + m.group(3)
                                + content.substring(m.end);
                    }
                }
                for (pair : replaceFirst) {
                    content = pair.left.matcher(content).replaceAll(pair.right);
                }
                for (pair : replaceAll) {
                    content = pair.left.matcher(content).replaceAll(pair.right);
                }
                
                write(out, content);
                compare(content, original);
            ];
        }
    }
    
    def compare(String content, String original) {
        charsTotal.addAndGet(original.length);
        
        for (i : 0 .. Math::min(content.length, original.length)) {
            if (("" + content.charAt(i) == "x") && ("" + original.charAt(i) != "x")) {
                charsMasked.incrementAndGet;
            }
        }
    }
    
    val translateAll = new ArrayList<Pattern>();
    val replaceAll = new ArrayList<ImmutablePair<Pattern, String>>();
    val replaceFirst = new ArrayList<ImmutablePair<Pattern, String>>();
    
    new(String token) {
        this.token = token;
        
        if (token.equals("pro")) {
            
            // Replace a couple of imports
            replaceFirst.add(new ImmutablePair(compile('''import (org\.jooq\.(ArrayConstant|ArrayRecord|VersionsBetweenAndStep|impl\.ArrayRecordImpl|impl\.FlashbackTable.*?)|(com.microsoft.*?));'''), "// ..."));
            replaceFirst.add(new ImmutablePair(compile('''import static org\.jooq\.impl\.DSL\.(cube|grouping|groupingId|groupingSets);'''), "// ..."));
            
            // Replace the Java / Scala / Xtend license header
            replaceFirst.add(new ImmutablePair(compile('''(?s:/\*\*[\r\n] \* Copyright.*?eula[\r\n] \*/)'''), '''
/**
 * Copyright (c) 2009-2014, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: http://www.jooq.org/licenses
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */'''));
        
            for (d : SQLDialect::values.filter[d | d.commercial]) {
                
                // Remove commercial dialects from @Support annotations
                replaceAll.add(new ImmutablePair(compile('''(?s:(\@Support\([^\)]*?),\s*\b«d.name()»\b([^\)]*?\)))'''), "$1$2"));
                replaceAll.add(new ImmutablePair(compile('''(?s:(\@Support\([^\)]*?)\b«d.name()»\b,\s*([^\)]*?\)))'''), "$1$2"));
                replaceAll.add(new ImmutablePair(compile('''(?s:(\@Support\([^\)]*?)\s*\b«d.name()»\b\s*([^\)]*?\)))'''), "$1$2"));
                
                // Remove commercial dialects from assume*(...) listings
                replaceAll.add(new ImmutablePair(compile('''(?:(assume(?:Family|Dialect)NotIn.*\([^\)]*?),\s*\b«d.name()»\b([^\)]*\)))'''), "$1$2"));
                replaceAll.add(new ImmutablePair(compile('''(?:(assume(?:Family|Dialect)NotIn.*\([^\)]*?)\b«d.name()»\b,\s*([^\)]*\)))'''), "$1$2"));
                replaceAll.add(new ImmutablePair(compile('''(?:(assume(?:Family|Dialect)NotIn.*\([^\)]*?)\s*\b«d.name()»\b\s*([^\)]*\)))'''), "$1$2"));
                
                // Remove commercial dialects from Arrays.asList() expressions
                replaceAll.add(new ImmutablePair(compile('''(asList\([^\)]*?),\s*\b«d.name()»\b([^\)]*?\))'''), "$1$2"));
                replaceAll.add(new ImmutablePair(compile('''(asList\([^\)]*?)\b«d.name()»\b,\s*([^\)]*?\))'''), "$1$2"));
                replaceAll.add(new ImmutablePair(compile('''(asList\([^\)]*?)\s*\b«d.name()»\b\s*([^\)]*?\))'''), "$1$2"));
                
                // Remove commercial dialects from imports
                replaceAll.add(new ImmutablePair(compile('''import (static )?org\.jooq\.SQLDialect\.«d.name()»;'''), "// ..."));
                replaceAll.add(new ImmutablePair(compile('''import (static )?org\.jooq\.util\.«d.name().toLowerCase»\..*?;'''), "// ..."));
                replaceAll.add(new ImmutablePair(compile('''import (static )?org\.jooq\..*?(\b|(?<=_))«d.name().toUpperCase»(\b|(?=_)).*?;'''), "// ..."));
            }
        }
                
        // Remove sections of delimited code
        translateAll.add(compile('''(?s:(/\* \[«token»\])( \*.*?/\* )(\[/«token»\] \*/))'''));
        translateAll.add(compile('''(?s:(<!-- \[«token»\])( -->.*?<!-- )(\[/«token»\] -->))'''));
        translateAll.add(compile('''(?s:(# \[«token»\])()(?:.*?)(# \[/«token»\]))'''));
    }
}

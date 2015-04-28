import java.sql.Connection;
import java.util.Properties;

import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;

/**
 * Copyright (c) 2009-2015, Data Geekery GmbH (http://www.datageekery.com)
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

public class Redshift {
    public static void main(String[] args) {
        try {
            Class.forName("com.amazon.redshift.jdbc41.Driver");

            String url = "jdbc:redshift://jooq-user.cbjcxubu1adb.us-west-2.redshift.amazonaws.com:5439/dev";
            String user = "test";
            String password = "lkhy3h4hHD";


            Properties properties = new Properties();
            properties.setProperty("user", user);
            properties.setProperty("password", password);

            System.out.println("Connect");
            Connection cn = new com.amazon.redshift.jdbc41.Driver().connect(url, properties);

            System.out.println("Select");
            Result<Record> result = DSL.using(cn).fetch(
                " select"
              + "   cl.relname"
              + " , c.conkey"
              + " from pg_constraint c"
              + " join pg_namespace n on c.connamespace = n.oid"
              + " join pg_class cl on c.conrelid = cl.oid"
              + " where c.contype = 'p';");
            System.out.println(result);
    /*
            System.out.println(Routines.fOne(conf));
            System.out.println(DSL.using(conf).select(Routines.fOne()).fetchOne());
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

/**
 * Copyright (C) 2014-2015 SINTEF
 *
 *     Brian Elves�ter <brian.elvesater@sintef.no>
 *     Shahzad Karamat <shazad.karamat@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.modelbased.proasense.adapter.oracle;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConsumerInput {

    public Connection con;

    public OracleConsumerInput(String url, String username, String password) throws ClassNotFoundException, SQLException {
        // Create Oracle connection
        Class.forName("oracle.jdbc.driver.OracleDriver"); // lokasjonen p� driveren, har lagt den inn som jar i
        //intellij sitt lib.

        //navnet p� getConnection er hentet fra addressen som er i minOracle databasen sin properties.
       // this.con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521", "SYSTEM", "1234");
       this.con = DriverManager.getConnection(url, username, password);
    }
}

/**
 * Copyright (C) 2014-2015 SINTEF
 *
 *     Brian Elvesæter <brian.elvesater@sintef.no>
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

import net.modelbased.proasense.adapter.base.AbstractBaseAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public abstract class AbstractOracleAdapter extends AbstractBaseAdapter {
    protected OracleConsumerInput inputPort;

    public AbstractOracleAdapter() throws SQLException, ClassNotFoundException {
        // Oracle input port properties

        this.inputPort = new OracleConsumerInput();
        Connection con = inputPort.con;
/*        java.sql.PreparedStatement statement = con.prepareStatement("select * from SCRAP");
        ResultSet result = statement.executeQuery();
        while (result.next()){
            System.out.println(result.getString(1) + " " + result.getString(2));
        }*/
    }

}

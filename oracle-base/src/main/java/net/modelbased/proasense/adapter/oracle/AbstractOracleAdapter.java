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
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;



public abstract class AbstractOracleAdapter extends AbstractBaseAdapter {
    protected OracleConsumerInput inputPort;

    public final static Logger logger = Logger.getLogger(AbstractOracleAdapter.class);

    public AbstractOracleAdapter() throws SQLException, ClassNotFoundException {
        // Oracle input port properties

        String url = adapterProperties.getProperty("proasense.adapter.oracle.url");
        String username = adapterProperties.getProperty("proasense.adapter.oracle.username");
        String password = adapterProperties.getProperty("proasense.adapter.oracle.password");
        //System.out.println("url er " + sensorId + " username " + username + " password " + sensorId);


        this.inputPort = new OracleConsumerInput(url, username, password);
        Connection con = inputPort.con;
        java.sql.PreparedStatement statement = con.prepareStatement("select * from IMM_6128649");
        ResultSet result = statement.executeQuery();

        while (result.next()){

           // System.out.println(result.getString(1) + " " + result.getString(2));
            convertToSimpleEvent(result);
        }
    }

    //hittil er strukturen det samme som for Montrac.
    //huske å bruke logget.debug("...");
    protected abstract void convertToSimpleEvent(ResultSet values) throws SQLException;
}

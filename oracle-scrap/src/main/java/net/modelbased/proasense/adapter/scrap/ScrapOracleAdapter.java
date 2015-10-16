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
package net.modelbased.proasense.adapter.scrap;

import net.modelbased.proasense.adapter.oracle.AbstractOracleAdapter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


public class ScrapOracleAdapter extends AbstractOracleAdapter {
    public final static Logger logger = Logger.getLogger(ScrapOracleAdapter.class);


    public ScrapOracleAdapter() throws SQLException, ClassNotFoundException, InterruptedException {
        // Get specific adapter properties
        String S_WSDL_URL = adapterProperties.getProperty("proasense.adapter.webservice.wsdl.url");
        boolean B_TEST_ENABLED = new Boolean(adapterProperties.getProperty("proasense.adapter.riglogger.test.enabled")).booleanValue();
        int I_CONFIG_TIMEDELAY = new Integer(adapterProperties.getProperty("proasense.adapter.riglogger.config.timedelay")).intValue();
        String[] S_CONFIG_POINTS = adapterProperties.getProperty("proasense.adapter.riglogger.config.points").split(",");
    }


    @Override
    protected int convertToSimpleEvent(int prevCount, Connection con, HashMap map, HashMap<String, HashMap> idToMap, String nameAndDate, String machineId) throws SQLException, InterruptedException {
        return 0;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        new ScrapOracleAdapter();
    }
}

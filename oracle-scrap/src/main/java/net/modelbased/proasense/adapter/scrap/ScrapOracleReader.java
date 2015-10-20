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

import eu.proasense.internal.ComplexValue;
import eu.proasense.internal.SimpleEvent;
import eu.proasense.internal.VariableType;
import net.modelbased.proasense.adapter.oracle.AbstractOracleAdapter;
import net.modelbased.proasense.adapter.oracle.OracleConsumerInput;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;


public class ScrapOracleReader implements Runnable {
    public final static Logger logger = Logger.getLogger(ScrapOracleReader.class);

    private BlockingQueue<SimpleEvent> queue;
    private long startTime;
    private Connection con;
    private String sensor_id;

    public ScrapOracleReader(BlockingQueue<SimpleEvent> queue, ScrapConfig scrapConfig, long startTime, OracleConsumerInput inputPort, String sensor_id) throws SQLException, ClassNotFoundException, InterruptedException {
        this.queue = queue;
        this.startTime = startTime;
        this.sensor_id = sensor_id;
        con = inputPort.con;
    }


    @Override
    public void run() {

        java.sql.PreparedStatement statement = null;

        try {
            statement = con.prepareStatement("select ANLAGE_DATE as CREATED_DATE, ANLAGE_TIME as CREATED_TIME,\n" +
                    "AUFTRAGS_BESTAND.BEARB_DATE, AUFTRAGS_BESTAND.BEARB_TIME,\n" +
                    "AUFTRAGS_BESTAND.MASCH_NR as MACHINE_NO,\n" +
                    "AUFTRAGS_BESTAND.AUFTRAG_NR as ORDER_OPERATION_NO,\n" +
                    "ADE_AUFTRAGMENGEN.IST_PRI as SCRAP_COUNT, ADE_GRUND_TEXTE.GRUNDTEXT as SCRAP_REASON,\n" +
                    "ARTIKEL as FINAL_ARTICLE\n" +
                    "from AUFTRAGS_BESTAND\n" +
                    "\n" +
                    "join ADE_AUFTRAGMENGEN  on\n" +
                    "ADE_AUFTRAGMENGEN.AUFTRAG_NR = AUFTRAGS_BESTAND.AUFTRAG_NR\n" +
                    "join ADE_GRUND_TEXTE on\n" +
                    "ADE_AUFTRAGMENGEN.GRUND_TEXT = ADE_GRUND_TEXTE.GRUNDTEXT_NR\n" +
                    "join AUFTRAG_STATUS on\n" +
                    "AUFTRAGS_BESTAND.AUFTRAG_NR = AUFTRAG_STATUS.AUFTRAG_NR\n" +
                    "\n" +
                    "where AUFTRAG_STATUS.A_STATUS = 'E'\n" +
                    "and\n" +
                    "AUFTRAGS_BESTAND.MASCH_NR IS NOT NULL");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            // 1. Query database every POLaL_TIME and get results
            ResultSet result = statement.executeQuery();
            SimpleEvent event = null;
            while (result.next()) {
                System.out.println(result.getString(9));
                // 2. Convert to simple events

                // event = convertToSimpleEvent(result);
            }
            // 3. Put simple events on queue
            //queue.put(event);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private SimpleEvent convertToSimpleEvent(ResultSet values) throws SQLException {
        //give value to every item from each row from database.
        String workplace = values.getString(1);
        String type = values.getString(2);
        String scrap = values.getString(3);
        String reasonText = values.getString(4);
        String designation = values.getString(5);
        String finalArticle = values.getString(6);
        String measurementTime = values.getString(7);   //timestamp

        //Conversion of date from string to long.
        long convertDate_timeStamp = 0;
        String sensorId = sensor_id;

        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss");
        try {
            Date date = dateFormat.parse(measurementTime);
            convertDate_timeStamp = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }


        SimpleEvent simpleEvent = new SimpleEvent();
        Map<String, ComplexValue> eventProperties = new HashMap<String, ComplexValue>();

        ComplexValue complexValue = new ComplexValue();
        complexValue.setValue(workplace);
        complexValue.setType(VariableType.STRING);
        eventProperties.put("machineId", complexValue); // workplace kan forandres, usikker på hva som er ID.

        complexValue = new ComplexValue();
        complexValue.setValue(type);
        complexValue.setType(VariableType.STRING);
        eventProperties.put("type", complexValue);

        complexValue = new ComplexValue();
        complexValue.setValue(scrap);
        complexValue.setType(VariableType.LONG);
        eventProperties.put("scrap", complexValue);

        complexValue = new ComplexValue();
        complexValue.setValue(reasonText);
        complexValue.setType(VariableType.STRING);
        eventProperties.put("reasonText", complexValue);

        complexValue = new ComplexValue();
        complexValue.setValue(designation);
        complexValue.setType(VariableType.STRING);
        eventProperties.put("designation", complexValue);

        complexValue = new ComplexValue();
        complexValue.setValue(finalArticle);
        complexValue.setType(VariableType.STRING);
        eventProperties.put("finalArticle", complexValue);

        return simpleEvent;
    }

}

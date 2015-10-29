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
import java.text.ParseException;
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
    String currentTime;
    String prevTime;
    boolean firstPoll = false;
    ScrapConfig scrapConfig;


    public ScrapOracleReader(BlockingQueue<SimpleEvent> queue, ScrapConfig scrapConfig, long startTime, OracleConsumerInput inputPort, String sensor_id) throws SQLException, ClassNotFoundException, InterruptedException {
        this.queue = queue;
        this.startTime = startTime;
        this.sensor_id = sensor_id;
        this.con = inputPort.con;
        this.scrapConfig = scrapConfig;
    }


    @Override
    public void run() {

        java.sql.PreparedStatement statement = null;
        long pollTime = scrapConfig.getPollInterval();


        while (true) {

            try {
                generateDates(con);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            logger.debug("polltime is "+pollTime);
            logger.debug(prevTime);

            try {
                statement = con.prepareStatement(
                        "select AUFTRAGS_BESTAND.BEARB_DATE as MEASUREMENT_TIME,\n" +
                                "AUFTRAGS_BESTAND.MASCH_NR as MACHINE_NO,\n" +
                                "IST_PRI as SCRAP_COUNT, GRUND_TEXT as SCRAP_DESIGNATIONREASON,\n" +
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
                                "AUFTRAGS_BESTAND.MASCH_NR IS NOT NULL\n" +
                                "and\n" +
                                "AUFTRAGS_BESTAND.BEARB_DATE between TO_DATE("+prevTime+", 'DD-MM-YYYY HH24:MI:SS') and TO_DATE(TO_CHAR(CURRENT_DATE, 'DD-MM-YYYY HH24:MI:SS'),'DD-MM-YYYY HH24:MI:SS')");
                // '10-09-2015 20:00:00'
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                // 1. Query database every POLL_TIME and get results
                ResultSet result = statement.executeQuery();
                SimpleEvent event = null;


                while (result.next()) {

                    // 2. Convert to simple events
                    logger.debug(result.toString());
                    event = convertToSimpleEvent(result);

                    // 3. Put simple events on queue

                    try {
                        logger.debug("sending event "+event);
                        queue.put(event);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    logger.debug("Finished with one simpleEvent " + event.toString());
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(pollTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private SimpleEvent convertToSimpleEvent(ResultSet values) throws SQLException {
        //give value to every item from each row from database.
        String CREATED_DATE = values.getString(1);
        String MACHINE_NO = values.getString(2);
        String SCRAP_COUNT = values.getString(3);
        String SCRAP_REASON = values.getString(4);
        String FINAL_ARTICLE = values.getString(5);

        SimpleEvent simpleEvent = createPrefix(sensor_id, CREATED_DATE);

        simpleEvent.putToEventProperties("machineId", createFullSimpleEvent(MACHINE_NO, VariableType.STRING));
        simpleEvent.putToEventProperties("quantity", createFullSimpleEvent(SCRAP_COUNT, VariableType.LONG));
        simpleEvent.putToEventProperties("designation", createFullSimpleEvent(SCRAP_REASON, VariableType.STRING));

        if(SCRAP_REASON.equals("1011") || SCRAP_REASON.equals("2011")){
            simpleEvent.putToEventProperties("goodPart", createFullSimpleEvent("true", VariableType.BOOLEAN));
        }else{
            simpleEvent.putToEventProperties("goodPart", createFullSimpleEvent("false", VariableType.BOOLEAN));
        }
        simpleEvent.putToEventProperties("finalArticle", createFullSimpleEvent(FINAL_ARTICLE, VariableType.STRING));

        return simpleEvent;
    }

    SimpleEvent createPrefix(String sensorId, String firstDate){
        SimpleEvent simpleEvent = new SimpleEvent();

        long convertDate_timeStamp = 0;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date date = dateFormat.parse(firstDate);
            convertDate_timeStamp = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }

        logger.debug("converted Date is "+convertDate_timeStamp);
        logger.debug("first Date is "+firstDate);
        simpleEvent.setSensorId(sensorId);
        simpleEvent.setTimestamp(convertDate_timeStamp);
        return simpleEvent;
    }

    ComplexValue createFullSimpleEvent(String value, VariableType type){
        ComplexValue complexValue = new ComplexValue();
        complexValue.setValue(value);
        complexValue.setType(type);

        return complexValue;
    }

    public void generateDates(Connection con) throws SQLException, ParseException {

        if(!firstPoll){
            prevTime = "'"+makeFirstDelay(scrapConfig.getDateDelay())+"'";

            java.sql.PreparedStatement statement = con.prepareStatement("SELECT TO_CHAR\n" +
                    "    (SYSDATE, 'DD-MM-YYYY HH24:MI:SS') \"NOW\"\n" +
                    "     FROM DUAL");

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            currentTime = resultSet.getString(1);
            firstPoll = true;
        }else {

            java.sql.PreparedStatement statement = con.prepareStatement("SELECT TO_CHAR\n" +
                    "    (SYSDATE, 'DD-MM-YYYY HH24:MI:SS') \"NOW\"\n" +
                    "     FROM DUAL");

            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            prevTime = currentTime;
            currentTime = resultSet.getString(1);
        }
    }

    public String makeFirstDelay(long delayAddedTime) throws ParseException {

        Date prevDateTime = new Date(delayAddedTime);
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String generatedDate = df.format(prevDateTime);

        System.out.println("generated delay is "+generatedDate);
        return generatedDate;
    }
}

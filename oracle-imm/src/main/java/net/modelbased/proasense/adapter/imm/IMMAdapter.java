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
package net.modelbased.proasense.adapter.imm;

import com.sun.media.jfxmedia.logging.Logger;
import eu.proasense.internal.ComplexValue;
import eu.proasense.internal.SimpleEvent;
import eu.proasense.internal.VariableType;
import net.modelbased.proasense.adapter.oracle.AbstractOracleAdapter;
import org.joda.time.LocalDate;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class IMMAdapter extends AbstractOracleAdapter {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
    new IMMAdapter();
    }

    public IMMAdapter() throws SQLException, ClassNotFoundException, InterruptedException {
        super();

    }

    protected int convertToSimpleEvent(int prevCount, Connection con, HashMap map, ArrayList ref_id_mapping, String nameAndDate) throws SQLException, InterruptedException {

        LocalDate localDate = new LocalDate();
        String[] tableNameAndDate = nameAndDate.split(",");
        String creationDate[] = tableNameAndDate[1].split(" ");
        String tableName = tableNameAndDate[0];
        String sensor_id = ref_id_mapping.get(0).toString();
        int newRowCount = 0;

        while(true){
            Thread.sleep(3000);
            java.sql.PreparedStatement statement = con.prepareStatement("select count(*) from "+tableName);
            ResultSet result = statement.executeQuery();


            if(result.next())newRowCount = Integer.parseInt(result.getString(1));
            //System.out.println(result.getString(1));
            if(creationDate[0].compareTo(localDate.toString()) < 0){
                try {
                    if(prevCount < newRowCount) createDelay(con, tableName, map, sensor_id, prevCount, newRowCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prevCount = newRowCount;
                System.out.println("prevCount = "+prevCount+" newCount = "+newRowCount);
                return prevCount;
            }

            try {
                if(newRowCount > prevCount)createDelay(con, tableName, map, sensor_id, prevCount, newRowCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prevCount = newRowCount;
            System.out.println("prevCount = "+prevCount+" newCount = "+newRowCount);
        }
    }

    protected void processTables(String sensorId, long timeStamp, String characteristic, ComplexValue complexValue){

        SimpleEvent simpleEvent = new SimpleEvent();
        simpleEvent.setSensorId(sensorId);
        simpleEvent.setTimestamp(timeStamp);
        simpleEvent.putToEventProperties(characteristic, complexValue);
        outputPort.publishSimpleEvent(simpleEvent);
        System.out.println(simpleEvent.toString());

    }

    public void createAndPublishEvent(long timestamp, String ref_id, String objectId, ComplexValue complexValue){
        Map<String, ComplexValue> eventProperties = new HashMap<String, ComplexValue>();

        complexValue.setValue(ref_id); // finne ut om denne er nødvendig da den er gjentatt i metodekallet også.
        eventProperties.put(objectId, complexValue);
        SimpleEvent event =  outputPort.createSimpleEvent(ref_id, timestamp, eventProperties);
        event.setSensorId("moulding");
        outputPort.publishSimpleEvent(event);
    }

    public void createDelay(Connection con, String tableName, HashMap map, String sensor_id,
    int prevCount, int newCount) throws InterruptedException, SQLException {

        java.sql.PreparedStatement statement = con.prepareStatement("SELECT *\n" +
                " FROM(\n" +
                "     SELECT ROW_NUMBER() OVER (ORDER BY CAPTURE_TS asc) AS rowNo, \n" +
                "             capture_ts, CAPTURE_TOFF, REFERENCE_ID, OBJECT_ID, VALUE_CHAR, VALUE_INTEGER, VALUE_DECIMAL, VALUE_DATETIME\n" +
                "             from "+tableName+")\n" +
                "             where  rowNo BETWEEN "+prevCount+" and "+newCount);

        ResultSet result = statement.executeQuery();

        while (result.next()){
           // System.out.println(result.getString(5));
            if(map.containsKey(result.getString(5))){

                String mappedVlues = (String) map.get(result.getString(5));
                String splitValue[] = splitValues(mappedVlues);
                long newDateObject = convertDate(result.getString(2));
                String extractedValue = "";
                ComplexValue complexValue = new ComplexValue();

                if(splitValue[1].equals("STRING")){
                    extractedValue = result.getString(3);
                    complexValue.setType(VariableType.STRING);
                }else if(splitValue[1].equals("DOUBLE")){
                    extractedValue = result.getString(7);
                    complexValue.setType(VariableType.DOUBLE);
                }

                complexValue.setValue(extractedValue);
                processTables(sensor_id, newDateObject, splitValue[0], complexValue);
            }
        }
    }
}

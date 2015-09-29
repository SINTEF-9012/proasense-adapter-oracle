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
import java.util.*;


public class IMMAdapter extends AbstractOracleAdapter {

    String sensorId;
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
    new IMMAdapter();
    }

    public IMMAdapter() throws SQLException, ClassNotFoundException, InterruptedException {
        super();

    }

    protected int convertToSimpleEvent(int prevCount, Connection con, HashMap map,HashMap idToMap,
                                       String nameAndDate, String sensorId) throws SQLException, InterruptedException {
        this.sensorId = sensorId;
        LocalDate localDate = new LocalDate();
        String[] tableNameAndDate = nameAndDate.split(",");
        String creationDate[] = tableNameAndDate[1].split(" ");
        String tableName = tableNameAndDate[0];
        int newRowCount = 0;

        while(true){
            Thread.sleep(3000);
            java.sql.PreparedStatement statement = con.prepareStatement("select count(*) from "+tableName);
            ResultSet result = statement.executeQuery();


            if(result.next())newRowCount = Integer.parseInt(result.getString(1));
            //System.out.println(newRowCount+" tableName "+tableName);

            if(creationDate[0].compareTo(localDate.toString()) < 0){
                try {
                    if(prevCount < newRowCount) filterData(con, tableName, map, idToMap, prevCount, newRowCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prevCount = newRowCount;
               // System.out.println("prevCount = "+prevCount+" newCount = "+newRowCount);
                return prevCount;
            }

            try {
                if(newRowCount > prevCount)filterData(con, tableName, map, idToMap, prevCount, newRowCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prevCount = newRowCount;
            //
            // System.out.println("prevCount = "+prevCount+" newCount = "+newRowCount);
        }
    }

    public void filterData(Connection con, String tableName, HashMap map, HashMap<String, HashMap> idToMap,
    int prevCount, int newCount) throws InterruptedException, SQLException {

        java.sql.PreparedStatement statement = con.prepareStatement("SELECT *\n" +
                " FROM(\n" +
                "     SELECT ROW_NUMBER() OVER (ORDER BY CAPTURE_TS asc) AS rowNo, \n" +
                "             capture_ts, CAPTURE_TOFF, REFERENCE_ID, OBJECT_ID, VALUE_CHAR, VALUE_INTEGER, VALUE_DECIMAL, VALUE_DATETIME\n" +
                "             from "+tableName+")\n" +
                "             where  rowNo BETWEEN "+prevCount+" and "+newCount);

        ResultSet result = statement.executeQuery();

        while (result.next()){

            if(map.containsKey(result.getString(5))){
                long date = convertDate(result.getString(2));
            String id_mapping = (String)map.get(result.getString(5));
            String[] split_id_mapping = splitValues(id_mapping);
            String refId = result.getString(4);
                HashMap tempMap = idToMap.get(refId);

                if(split_id_mapping[1].equals("STRING")){
                    if(!tempMap.containsKey(split_id_mapping[0]))
                        tempMap.put(split_id_mapping[0] + "," + split_id_mapping[1], refId);
                    if(tempMap.size() == 8){
                        outputToBroker(date, tempMap);
                        tempMap.clear();
                        tempMap.put("machineId,STRING", refId);
                    }
                }else if(split_id_mapping[1].equals("DOUBLE")){
                    if(!tempMap.containsKey(split_id_mapping[0])){
                        tempMap.put(split_id_mapping[0]+","+split_id_mapping[1], result.getString(8));
                       // System.out.println("split_id er "+split_id_mapping[0] + " str er "+tempMap.size());
                    }
                    if(tempMap.size() == 8){
                        outputToBroker(date, tempMap);
                        tempMap.clear();
                        tempMap.put("machineId,STRING",refId);
                    }
                }
            }
        }
    }

    public String[] splitValues(String valuesToSplit){
        String[] splitTwoValues = valuesToSplit.split(":");
        return splitTwoValues;
    }

    public void outputToBroker(long date, HashMap<String, HashMap> mapValueAndType){
        SimpleEvent event = new SimpleEvent();
        event.setTimestamp(date);
        event.setSensorId(sensorId);

        String objectId = "";

        for(String key: mapValueAndType.keySet()){

            String[] objectIdAndType = key.split(",");
            objectId = objectIdAndType[0];
            String value = String.valueOf(mapValueAndType.get(key));
            ComplexValue complexValue = new ComplexValue();
            complexValue.setValue(value);

            if(objectIdAndType[1].equals("STRING")){
                complexValue.setType(VariableType.STRING);
            }else if(objectIdAndType[1].equals("DOUBLE")){
                complexValue.setType(VariableType.DOUBLE);
            }
            event.putToEventProperties(objectId, complexValue);

        }
        System.out.println(event.toString());
        outputPort.publishSimpleEvent(event);
    }
}

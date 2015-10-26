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
import net.modelbased.proasense.adapter.oracle.OracleConsumerInput;
import org.apache.log4j.Logger;
import org.joda.time.LocalDate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;


public class IMMOracleAdapter extends AbstractOracleAdapter {


    public final static Logger logger = Logger.getLogger(AbstractOracleAdapter.class);
    HashMap objectToValueMap = null;
    HashMap<String, HashMap> idToMap = null;

    String reference_id_mapping = adapterProperties.getProperty("proasense.adapter.oracle.imm.reference_id.mapping");
    String reference_id_tags = adapterProperties.getProperty("proasense.adapter.oracle.imm.reference_id.tags");
    // gir alle ord vi skal sammenligne med, som cycleTime og hvordan mappingen er, eks cycleTime:DOUBLE
    String object_id_tag = adapterProperties.getProperty("proasense.adapter.oracle.imm.object_id.tags");
    String object_id_mapping = adapterProperties.getProperty("proasense.adapter.oracle.imm.object_id.mapping");
    String globalTableName = adapterProperties.getProperty("proasense.adapter.imm.DBTableName1");


    String id_tags[] = reference_id_tags.split(",");
    String objectTag[] = object_id_tag.split(",");
    String objectValue[] = object_id_mapping.split(",");




    HashMap createNameMap(String[] objectId, String[] nameAndValues){
        HashMap map = new HashMap();

        for(int i = 0; i < objectId.length; i++){
            map.put(objectId[i], nameAndValues[i]);
        }
        return map;
    }


    public long convertDate(String date){
        long timestamp = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
        try {
            Date convertedDate = dateFormat.parse(date);
            timestamp = convertedDate.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timestamp;
    }

    public void  checkForLatestTable(Connection con, String globalTableName) throws SQLException, InterruptedException {

        if(globalTableName.length() < 5){
            System.out.println("Name of tha table should be of more than 4 characters of length!");
            return;
        }
        String newTableName = "'"+trimTableName(globalTableName)+"%'";
        String prevTableName = "";
        int rowCount = 0;
        int prevCount = 0;



        ResultSet resultSet = null;

        while(true) {

            java.sql.PreparedStatement statement = con.prepareStatement("select * from(select object_name, created from SYS.USER_OBJECTS where object_name like "+newTableName+"\n" +
                    "order by created desc) where created like SYSDATE and rownum = 1");

                logger.debug("waiting for valid table...");
            resultSet = statement.executeQuery();

            if(resultSet.next()){
                if(!(resultSet.getString(1).equals(prevTableName))) prevCount = 0;
                rowCount =  convertToSimpleEvent(prevCount,con, objectToValueMap, idToMap ,
                        resultSet.getString(1)+","+resultSet.getString(2), sensor_id);
            }else {

                try {
                    Thread.sleep(pollIntervall);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            prevCount = rowCount;
            prevTableName = resultSet.getString(1);
            logger.debug("Back in outer loop.");
        }
    }


    String trimTableName(String nameFromProperties){
        return nameFromProperties.substring(0, 5);
    }

    HashMap<String, HashMap> createIdToValueMap(String refId, String[] id_tags){
        HashMap<String, HashMap> machines = new HashMap<String, HashMap>();
        HashMap values = new HashMap();

        String[] ref_id_tag = refId.split(":");

        for(int i = 0; i < id_tags.length; i++){
            values.put(ref_id_tag[0] +","+ ref_id_tag[1], id_tags[i]);
            machines.put(id_tags[i], values);
        }
        return  machines;
    }

    public IMMOracleAdapter() throws SQLException, ClassNotFoundException, InterruptedException {
        super();

        objectToValueMap = createNameMap(objectTag, objectValue);
        idToMap = createIdToValueMap(reference_id_mapping, id_tags);
        Connection con = inputPort.con;

        checkForLatestTable(con, globalTableName);

    }

    protected int convertToSimpleEvent(int prevCount, Connection con, HashMap map,HashMap idToMap,
                                       String nameAndDate, String sensorId) throws SQLException, InterruptedException {

        LocalDate localDate = new LocalDate();
        String[] tableNameAndDate = nameAndDate.split(",");
        String creationDate[] = tableNameAndDate[1].split(" ");
        String tableName = tableNameAndDate[0];
        int newRowCount = 0;

        ResultSet result;
        java.sql.PreparedStatement statement;


        while(true){
            Thread.sleep(pollIntervall);

            statement = con.prepareStatement("select count(*) from " + tableName);
            result = statement.executeQuery();

            logger.debug("still in inner loop");
            if(result.next()){
                newRowCount = Integer.parseInt(result.getString(1));
            }

            if(creationDate[0].compareTo(localDate.toString()) < 0){
                try {
                    if(prevCount < newRowCount) filterData(con, tableName, map, idToMap, prevCount, newRowCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prevCount = newRowCount;
                return prevCount;
            }

            try {
                if(newRowCount > prevCount)filterData(con, tableName, map, idToMap, prevCount, newRowCount);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            prevCount = newRowCount;
            statement.close();
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
                    if(tempMap.size() == 7){
                        outputToBroker(date, tempMap);
                        tempMap.clear();
                        tempMap.put("machineId,STRING", refId);
                    }
                }else if(split_id_mapping[1].equals("DOUBLE")){
                    if(!tempMap.containsKey(split_id_mapping[0])){
                        tempMap.put(split_id_mapping[0]+","+split_id_mapping[1], result.getString(8));
                    }
                    if(tempMap.size() == 7){
                        outputToBroker(date, tempMap);
                        tempMap.clear();
                        tempMap.put("machineId,STRING",refId);
                    }
                }
            }
        }
        statement.close();
    }

    public String[] splitValues(String valuesToSplit){
        String[] splitTwoValues = valuesToSplit.split(":");
        return splitTwoValues;
    }

    public void outputToBroker(long date, HashMap<String, HashMap> mapValueAndType){

        SimpleEvent event = new SimpleEvent();
        event.setTimestamp(date);
        event.setSensorId(sensor_id);

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        new IMMOracleAdapter();
    }
}

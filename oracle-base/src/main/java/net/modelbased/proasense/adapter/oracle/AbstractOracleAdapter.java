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

import eu.proasense.internal.ComplexValue;
import eu.proasense.internal.VariableType;
import net.modelbased.proasense.adapter.base.AbstractBaseAdapter;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public abstract class AbstractOracleAdapter extends AbstractBaseAdapter {
    protected OracleConsumerInput inputPort;

    public final static Logger logger = Logger.getLogger(AbstractOracleAdapter.class);
    HashMap objectToValueMap = null;
    HashMap<String, HashMap> idToMap = null;
    String sensorId;
    long delay;
    public AbstractOracleAdapter() throws SQLException, ClassNotFoundException, InterruptedException {
        // Oracle input port properties

        String url = adapterProperties.getProperty("proasense.adapter.oracle.url");
        String username = adapterProperties.getProperty("proasense.adapter.oracle.username");
        String password = adapterProperties.getProperty("proasense.adapter.oracle.password");
        String globalTableName = adapterProperties.getProperty("proasense.adapter.oracle.DBTableName1");
        String rawDelayValue = adapterProperties.getProperty("proasense.adapter.oracle.poll.interval");
        String sid = adapterProperties.getProperty("proasense.adapter.oracle.sid");
        delay = Long.parseLong(rawDelayValue);
        // gir moulding som sensorId
        String sensor_id = adapterProperties.getProperty("proasense.adapter.base.sensorid");
        this.sensorId = sensor_id;
        // gir nr på maskinene  som er mappet til og id_tags som er sensorId:String
        String reference_id_mapping = adapterProperties.getProperty("proasense.adapter.oracle.imm.reference_id.mapping");
        String reference_id_tags = adapterProperties.getProperty("proasense.adapter.oracle.imm.reference_id.tags");
        // gir alle ord vi skal sammenligne med, som cycleTime og hvordan mappingen er, eks cycleTime:DOUBLE
        String object_id_tag = adapterProperties.getProperty("proasense.adapter.oracle.imm.object_id.tags");
        String object_id_mapping = adapterProperties.getProperty("proasense.adapter.oracle.imm.object_id.mapping");

        String id_tags[] = reference_id_tags.split(",");
        String objectTag[] = object_id_tag.split(",");
        String objectValue[] = object_id_mapping.split(",");

        objectToValueMap = createNameMap(objectTag, objectValue);
        idToMap = createIdToValueMap(reference_id_mapping, id_tags); // alle id fikk en mapping med id-nr og string type.
        this.inputPort = new OracleConsumerInput(url+"/"+sid, username, password);
        Connection con = inputPort.con;

        checkForLatestTable(con, globalTableName);
    }

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
        String newTableName = "'"+trimTableName(globalTableName)+"%'";
        String prevTableName = "";
        int rowCount = 0;
        int prevCount = 0;

        while(true) {

            java.sql.PreparedStatement statement = con.prepareStatement("select * from(select object_name, created " +
                    "from SYS.USER_OBJECTS where object_name like "+newTableName+" order by created desc)\n" +
                    "  where rownum = 1");

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                if(!(resultSet.getString(1).equals(prevTableName))) prevCount = 0;
               rowCount =  convertToSimpleEvent(prevCount,con, objectToValueMap, idToMap ,
                       resultSet.getString(1)+","+resultSet.getString(2), sensorId);
            }

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
        //test w
    protected abstract int convertToSimpleEvent(int prevCount, Connection con, HashMap map,
                                                HashMap<String, HashMap> idToMap, String nameAndDate,
                                                String machineId) throws SQLException, InterruptedException;
}
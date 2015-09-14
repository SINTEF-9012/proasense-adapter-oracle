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
    HashMap map = null;
    ArrayList al = null;
    public AbstractOracleAdapter() throws SQLException, ClassNotFoundException, InterruptedException {
        // Oracle input port properties

        String url = adapterProperties.getProperty("proasense.adapter.oracle.url");
        String username = adapterProperties.getProperty("proasense.adapter.oracle.username");
        String password = adapterProperties.getProperty("proasense.adapter.oracle.password");
        String globalTableName = adapterProperties.getProperty("proasense.adapter.oracle.DBTableName1");
        String sensor_id = adapterProperties.getProperty("proasense.adapter.base.sensorid");
        String reference_id_tags = adapterProperties.getProperty("proasense.adapter.oracle.imm.reference_id.tags");
        String id_tags[] = reference_id_tags.split(",");
        String reference_id_mapping = adapterProperties.getProperty("proasense.adapter.oracle.imm.reference_id.mapping");
        String object_id_tag = adapterProperties.getProperty("proasense.adapter.oracle.imm.object_id.tags");
        String objectTag[] = object_id_tag.split(",");
        String object_id_mapping = adapterProperties.getProperty("proasense.adapter.oracle.imm.object_id.mapping");
        String objectValue[] = object_id_mapping.split(",");

        map = createNameMap(objectTag, objectValue);
        al = refIdAndMapping(sensor_id, reference_id_mapping, id_tags);
        this.inputPort = new OracleConsumerInput(url, username, password);
        Connection con = inputPort.con;

        checkForLatestTable(con, globalTableName);

    }

    //hittil er strukturen det samme som for Montrac.
    //huske å bruke logget.debug("...");
    protected abstract void convertToSimpleEvent(Connection con, HashMap map, ArrayList ref_id_mapping, String nameAndDate) throws SQLException, InterruptedException;

    protected abstract void processTables(String sensorId, long timeStamp, String characteristic, ComplexValue complexValue) throws SQLException;

    HashMap createNameMap(String[] objectId, String[] nameAndValues){
        HashMap map = new HashMap();

        for(int i = 0; i < objectId.length; i++){
            map.put(objectId[i], nameAndValues[i]);
        }
        return map;
    }

    public String[] splitValues(String valuesToSplit){
            String[] splitTwoValues = valuesToSplit.split(":");
        return splitTwoValues;
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
        while(true) {

            java.sql.PreparedStatement statement = con.prepareStatement("select * from(select object_name, created " +
                    "from SYS.USER_OBJECTS where object_name like "+newTableName+" order by created desc)\n" +
                    "  where rownum = 1");

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) convertToSimpleEvent(con, map, al, resultSet.getString(1)+","+resultSet.getString(2));

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("er i ytre while.");
        }
    }

    ArrayList refIdAndMapping(String sensor_id, String refId, String[] refMapping){
        ArrayList arrayList = new ArrayList();
        arrayList.add(0, sensor_id);
        arrayList.add(1, refId);

        for(int i = 0; i < refMapping.length; i++){
            arrayList.add(i+2, refMapping[i]);
        }
        return arrayList;
    }

    String trimTableName(String nameFromProperties){
        return nameFromProperties.substring(0, 5);
    }
}
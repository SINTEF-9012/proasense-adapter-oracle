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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;


public abstract class AbstractOracleAdapter extends AbstractBaseAdapter {
    protected OracleConsumerInput inputPort;

    public final static Logger logger = Logger.getLogger(AbstractOracleAdapter.class);

    public AbstractOracleAdapter() throws SQLException, ClassNotFoundException {
        // Oracle input port properties

        String url = adapterProperties.getProperty("proasense.adapter.oracle.url");
        String username = adapterProperties.getProperty("proasense.adapter.oracle.username");
        String password = adapterProperties.getProperty("proasense.adapter.oracle.password");
        String tableName = adapterProperties.getProperty("proasense.adapter.oracle.DBTableName1");
        String sensor_id = adapterProperties.getProperty("proasense.adapter.base.sensorid");
        String reference_id_tags = adapterProperties.getProperty("proasense.adapter.oracle.imm.reference_id.tags");
        String reference_id_mapping = adapterProperties.getProperty("proasense.adapter.oracle.imm.reference_id.mapping");
        String object_id_tag = adapterProperties.getProperty("proasense.adapter.oracle.imm.object_id.tags");
        String objectTag[] = object_id_tag.split(",");
        String object_id_mapping = adapterProperties.getProperty("proasense.adapter.oracle.imm.object_id.mapping");
        String objectValue[] = object_id_mapping.split(",");

        HashMap map = createNameMap(objectTag, objectValue);
        this.inputPort = new OracleConsumerInput(url, username, password);
        Connection con = inputPort.con;
        java.sql.PreparedStatement statement = con.prepareStatement("select * from "+tableName);
        ResultSet result = statement.executeQuery();

        while (result.next()){
           // convertToSimpleEvent(result);
        if(map.containsKey(result.getString(4))){
            String mappedVlues = (String) map.get(result.getString(4));
            String splitValue[] = splitValues(mappedVlues);
            long newDateObject = convertDate(result.getString(1));
            String extractedValue = "";
            ComplexValue complexValue = new ComplexValue();

            if(splitValue[1].equals("STRING")){
                extractedValue = result.getString(3);
                complexValue.setType(VariableType.STRING);
            }else if(splitValue[1].equals("DOUBLE")){
                extractedValue = result.getString(7);
                complexValue.setType(VariableType.DOUBLE);
            }else if(splitValue[1].equals("INTEGER")){
                extractedValue = result.getString(6);

            }else if(splitValue[1].equals("CHAR")){

                extractedValue = result.getString(5);
            }
            complexValue.setValue(extractedValue);
            convertToSimpleEvent(sensor_id, newDateObject, splitValue[0],complexValue);
            }
        }
    }

    //hittil er strukturen det samme som for Montrac.
    //huske å bruke logget.debug("...");
    protected abstract void convertToSimpleEvent(String sensorId, long timeStamp, String characteristic, ComplexValue complexValue);


    HashMap createNameMap(String[] objectId, String[] nameAndValues){
        HashMap map = new HashMap();

        for(int i = 0; i < objectId.length; i++){
            map.put(objectId[i], nameAndValues[i]);
        }
        return map;
    }

    String[] splitValues(String valuesToSplit){
            String[] splitTwoValues = valuesToSplit.split(":");
        return splitTwoValues;
    }

    long convertDate(String date){
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
}

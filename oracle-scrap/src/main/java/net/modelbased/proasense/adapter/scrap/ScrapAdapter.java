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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ScrapAdapter extends AbstractOracleAdapter {


    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        new ScrapAdapter();
    }

    public ScrapAdapter() throws SQLException, ClassNotFoundException, InterruptedException {
    }


    @Override
    protected int convertToSimpleEvent(int prevCount, Connection con, HashMap map, HashMap<String, HashMap> idToMap, String nameAndDate, String machineId) throws SQLException, InterruptedException {
        return 0;
    }


    protected void convertToSimpleEvent(ResultSet values) throws SQLException {

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
        String sensorId = workplace;

        DateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy H:m");
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

        simpleEvent.eventProperties = eventProperties;
        SimpleEvent event = outputPort.createSimpleEvent(sensorId, convertDate_timeStamp, eventProperties);
        logger.debug(event.toString());
        outputPort.publishSimpleEvent(event);
    }
}

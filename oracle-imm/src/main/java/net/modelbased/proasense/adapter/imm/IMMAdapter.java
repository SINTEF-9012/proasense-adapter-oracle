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

import java.lang.reflect.MalformedParameterizedTypeException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class IMMAdapter extends AbstractOracleAdapter {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    new IMMAdapter();
    }

    public IMMAdapter() throws SQLException, ClassNotFoundException {

    }


    protected void convertToSimpleEvent(String sensorId, long timeStamp, String characteristic, ComplexValue complexValue){

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
        System.out.println(event.toString());
    }
}

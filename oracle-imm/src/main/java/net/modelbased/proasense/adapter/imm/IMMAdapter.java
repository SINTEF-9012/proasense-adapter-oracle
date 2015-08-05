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
import net.modelbased.proasense.adapter.oracle.AbstractOracleAdapter;

import java.sql.ResultSet;
import java.sql.SQLException;


public class IMMAdapter extends AbstractOracleAdapter {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
    new IMMAdapter();
    }
    public IMMAdapter() throws SQLException, ClassNotFoundException {

    }


    protected void convertToSimpleEvent(ResultSet values) throws SQLException {
        logger.debug(values.getString(1) + " " + values.getString(2));
        String charecteristic = values.getString(1);
        String designation = values.getString(2);
        String targeValue = values.getString(3);
        String utl = values.getString(4);
        String upal = values.getString(5);
        String ltl = values.getString(6);
        String lpal = values.getString(7);
        String unit = values.getString(8);
        String measuredValue = values.getString(9);
        String measuredTimeS = values.getString(10);

        if(designation.equals("Dosing Time")){
            logger.debug("designation er "+designation);
        }else if(designation.equals("Cycle Time")){
            logger.debug("designation er "+designation);
        }else if(designation.equals("Movement Differential")){
            logger.debug("designation er "+designation);
        }else if(designation.equals("Cavity Pressure")){
            logger.debug("designation er "+designation);
        }else if(designation.equals("Injection Time")){
            logger.debug("designation er "+designation);
        }else if(designation.equals("Melt Cushion")){
            logger.debug("designation er "+designation);
        }else if(designation.equals("Jet Temperation ")){
            logger.debug("designation er "+designation);
        }else if(designation.equals("Cooling Time ")){

        }
    }
}

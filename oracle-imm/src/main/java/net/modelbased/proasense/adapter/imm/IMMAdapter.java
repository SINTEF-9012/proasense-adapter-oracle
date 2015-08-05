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
        System.out.println(values.getString(1) + " " + values.getString(2));
        String charecteristic = values.getString(0);
        String designation = values.getString(1);
        long targeValue = Long.parseLong(values.getString(2)); //konvertert til long.
        String utl = values.getString(3);
        String upal = values.getString(4);
        String ltl = values.getString(5);
        String lpal = values.getString(6);
        String unit = values.getString(7);
        String measuredValue = values.getString(8);
        String measuredTimeS = values.getString(9);
    }
}

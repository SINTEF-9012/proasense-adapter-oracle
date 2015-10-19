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

import eu.proasense.internal.SimpleEvent;
import net.modelbased.proasense.adapter.oracle.AbstractOracleAdapter;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ScrapOracleAdapter extends AbstractOracleAdapter {
    public final static Logger logger = Logger.getLogger(ScrapOracleAdapter.class);


    public ScrapOracleAdapter() throws SQLException, ClassNotFoundException, InterruptedException {
        // Get specific adapter properties
        int I_CONFIG_TIMEDELAY = new Integer(adapterProperties.getProperty("proasense.adapter.scrap.config.timedelay")).intValue();

        // Configure scrap adapter
        ScrapConfig scrapConfig = new ScrapConfig(this.sensor_id, I_CONFIG_TIMEDELAY);

        // Set initial start time (adjusted with time delay)
        int subtractMinutes = 0 - I_CONFIG_TIMEDELAY;
        long startTime = System.currentTimeMillis() - (subtractMinutes*60*1000);

        // Blocking queue for multi-threaded application
        int NO_BLOCKINGQUEUE_SIZE = 1000000;
        BlockingQueue<SimpleEvent> queue = new ArrayBlockingQueue<SimpleEvent>(NO_BLOCKINGQUEUE_SIZE);

        // Total number of threads
        int NO_TOTAL_THREADS = 2;

        // Create executor environment for threads
        ArrayList<Runnable> workers = new ArrayList<Runnable>(NO_TOTAL_THREADS);
        ExecutorService executor = Executors.newFixedThreadPool(NO_TOTAL_THREADS);

        // Create thread for Scrap reader
        workers.add(new ScrapOracleReader(queue, scrapConfig, startTime, this.inputPort, sensor_id));

        // Create thread for Scrap writer
        workers.add(new ScrapOracleWriter(queue, startTime, this.outputPort));

        // Execute all threads
        for (int i = 0; i < NO_TOTAL_THREADS; i++) {
            executor.execute(workers.get(i));
        }

        // Shut down executor
        executor.shutdown();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException, InterruptedException {
        new ScrapOracleAdapter();
    }
}

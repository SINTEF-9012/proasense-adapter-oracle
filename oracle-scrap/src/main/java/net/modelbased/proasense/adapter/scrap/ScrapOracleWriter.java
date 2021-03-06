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

import net.modelbased.proasense.adapter.base.KafkaProducerOutput;
import org.apache.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


public class ScrapOracleWriter implements Runnable {
    public final static Logger logger = Logger.getLogger(ScrapOracleWriter.class);

    private BlockingQueue<SimpleEvent> queue;
    private long startTime;
    private KafkaProducerOutput outputPort;

    long lastTimestamp;


    public ScrapOracleWriter(BlockingQueue<SimpleEvent> queue, long startTime, KafkaProducerOutput outputPort) {
        this.queue = queue;
        this.startTime = startTime;
        this.outputPort = outputPort;

        this.lastTimestamp = this.startTime;
    }


    @Override
    public void run() {
        while (true) {
            try {
                // Read simple event from queue
                SimpleEvent event = queue.take();
                // Check timestamp
                long eventTimestamp = event.getTimestamp();
                long timeDiff = eventTimestamp - this.lastTimestamp;
                if (timeDiff > 0) {
                    TimeUnit.MILLISECONDS.sleep(timeDiff);
                }

                // Publish simple event
                this.outputPort.publishSimpleEvent(event);
                logger.debug("simpleEvent = " + event.toString());

                // Update timestamps
                this.lastTimestamp = eventTimestamp;
            }
            catch (Exception e) {
                System.out.println(e.getClass().getName() + ": " + e.getMessage());
            }

            logger.debug("Event published.");
        }

    }
}

package vn.topmedia.monitor;

/*
 * Copyright (c) 2011 Topmedia Company
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import org.junit.Test;
import vn.topmedia.monitor.common.MonitorType;

/**
 *
 * @author tuanta@topmedia.vn
 */
public class MonitorServerTest {

    @Test
    public void testAlertMessage() {
        MonitorServer monitor = new MonitorServer(MonitorServer.class);
        int up = 1;
        for (int i = 0; i < 17 * up; i++) {
            monitor.info("8x77", "Console", "Loi he thong gateway the nay " + (i + 1));
            sleep();
        }
        for (int i = 0; i < 5 * up; i++) {
            monitor.debug("Application", MonitorType.CONSOLE, "Loi database roi " + (i + 1));
            sleep();
        }
        for (int i = 0; i < 5 * up; i++) {
            monitor.error("8x97", MonitorType.APPLICATION, "Application loi roi con gi " + (i + 1));
            sleep();
        }
        for (int i = 0; i < 23 * up; i++) {
            monitor.error("8x71", MonitorType.CDR, "Webserver loi gi the nay " + (i + 1));
            sleep();
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
        }
    }
}

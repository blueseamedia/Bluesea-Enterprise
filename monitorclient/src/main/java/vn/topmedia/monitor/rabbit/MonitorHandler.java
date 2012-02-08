/*
 * Copyright (c) 2011 Topmedia Company
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package vn.topmedia.monitor.rabbit;

import vn.topmedia.monitor.MonitorBean;
import vn.topmedia.monitor.commons.Alert;

/**
 * Handler message from Rabbit Server.
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class MonitorHandler {

    public void handleMessage(String text) {
        System.out.println("Received: " + text);
    }

    public void handleMessage(MonitorBean bean) {
        Alert.monitor(bean);
    }
}
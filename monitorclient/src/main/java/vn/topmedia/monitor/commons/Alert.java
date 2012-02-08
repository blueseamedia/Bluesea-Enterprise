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
package vn.topmedia.monitor.commons;

import java.awt.TrayIcon;
import vn.topmedia.monitor.MonitorBean;
import vn.topmedia.monitor.bean.MonitorSettingBean;
import vn.topmedia.monitor.common.MonitorLevel;
import vn.topmedia.monitor.common.MonitorType;
import vn.topmedia.monitor.form.MDIMain;
import vn.topmedia.monitor.form.SMSMonitor;

/**
 * Alert message by tool tip and beep system. Beep system only sound when run
 * application in console.
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class Alert extends Thread {

    private static MDIMain mdiMain;
    private static SMSMonitor monitor;

    public static MDIMain getMdiMain() {
        return Alert.mdiMain;
    }

    public static void setMdiMain(MDIMain mdiMain) {
        Alert.mdiMain = mdiMain;
    }

    public static SMSMonitor getMonitor() {
        return monitor;
    }

    public static void setMonitor(SMSMonitor monitor) {
        Alert.monitor = monitor;
    }

    /**
     * Tool tip message. Display info from message
     *
     * @param title
     * @param info
     */
    public static void displayMessage(String title, String info) {
        if (getMdiMain() == null) {
            return;
        }
        getMdiMain().getTrayIcon().displayMessage(title, info, TrayIcon.MessageType.ERROR);
    }

    /**
     * Beep with times. Only beep when run in console.
     *
     * @param times Time beep.
     */
    public static synchronized void beep(int times) {
        for (int i = 0; i < times; i++) {
            System.out.print("\007");
            System.out.flush();
        }
    }

    /**
     * Alert to monitor client
     *
     * @param monUser
     * @param bean
     */
    public static synchronized void alert(MonitorSettingBean monUser, MonitorBean bean) {
        if (monUser == null) {
            if (bean.getLevel() == MonitorLevel.ERROR && !bean.getServiceId().equals("Logger")) {
                displayMessage("[" + bean.getServiceId() + " - " + bean.getType() + "]", bean.getInfo());
                beep(1);
            }
        } else {
            if (bean.getLevel() == MonitorLevel.ERROR && !bean.getServiceId().equals("Logger") && !bean.getType().equals(MonitorType.CONSOLE)) {
                if (monUser.getShowTipError()) {
                    displayMessage("[" + bean.getServiceId() + " - " + bean.getType() + "]", bean.getInfo());
                }
                if (monUser.getAlertType() == Constants.ALERT_BEEP) {
                    beep(1);
                }
            }
        }
    }

    /**
     * Alert to monitor client
     *
     * @param bean
     */
    public static synchronized void monitor(MonitorBean bean) {
        if (getMonitor() != null) {
            getMonitor().alert(bean);
        } else {
            if (bean.getLevel() == MonitorLevel.ERROR && !bean.getServiceId().equals("Logger")) {
                displayMessage("[" + bean.getServiceId() + " - " + bean.getType() + "]", bean.getInfo());
                beep(1);
            }
        }
    }
}

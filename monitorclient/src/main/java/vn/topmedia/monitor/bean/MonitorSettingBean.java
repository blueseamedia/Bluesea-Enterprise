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
package vn.topmedia.monitor.bean;

import vn.topmedia.monitor.commons.Constants;
import vn.topmedia.monitor.form.MDIMain;

/**
 * Monitor Setting parameter
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class MonitorSettingBean {

    private String userName;
    private String password;
    private String vitualHost;
    private String userIP;
    private int userPort;
    private int alertType;
    private boolean debugMode = true;
    private boolean showTipError = true;

    public MonitorSettingBean(MDIMain mdi) {
        this.alertType = mdi.getAlertType();
    }

    public void setUserName(String value) {
        this.userName = value;
        Constants.RABBIT_USER = value;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        Constants.RABBIT_PASSWORD = password;
    }

    public String getVitualHost() {
        return vitualHost;
    }

    public void setVitualHost(String vitualHost) {
        this.vitualHost = vitualHost;
        Constants.RABBIT_VITUALHOST = vitualHost;
    }

    public void setUserIP(String value) {
        this.userIP = value;
        Constants.RABBIT_SERVER = value;
    }

    public String getUserIP() {
        return this.userIP;
    }

    public void setUserPort(int value) {
        this.userPort = value;
        Constants.RABBIT_PORT = value;
    }

    public int getUserPort() {
        return this.userPort;
    }

    public void setAlertType(int value) {
        this.alertType = value;
    }

    public int getAlertType() {
        return this.alertType;
    }

    public boolean getDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public void setShowTipError(boolean value) {
        this.showTipError = value;
    }

    public boolean getShowTipError() {
        return this.showTipError;
    }
}
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
package vn.topmedia.monitor;

import java.sql.Timestamp;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import vn.topmedia.monitor.common.MonitorLevel;

/**
 * Monitor information
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class MonitorBean implements java.io.Serializable {

    private String clazz;
    private String type;
    private MonitorLevel level;
    private String userId;
    private String serviceId;
    private String commandCode;
    private String info;
    private Timestamp time;
    private LoggingEvent loggingEvent;
    private Layout layout;

    public MonitorBean() {
        time = new Timestamp(System.currentTimeMillis());
    }

    public Layout getLayout() {
        return layout;
    }

    public LoggingEvent getLoggingEvent() {
        return loggingEvent;
    }

    public String getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(String commandCode) {
        this.commandCode = commandCode;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public MonitorLevel getLevel() {
        return level;
    }

    public void setLevel(MonitorLevel level) {
        this.level = level;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTime() {
        if (time == null) {
            time = new Timestamp(System.currentTimeMillis());
        }
        return time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getServiceId());
        if (getCommandCode() != null) {
            sb.append(" - ");
            sb.append(getCommandCode());
        }
        if (getUserId() != null) {
            sb.append(" - ");
            sb.append(getUserId());
        }
        sb.append(" - ");
        sb.append(getInfo());
        return sb.toString();
    }
}
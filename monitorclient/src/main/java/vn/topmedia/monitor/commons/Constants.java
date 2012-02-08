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

/**
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class Constants {

    public static final String CFG_FILE_NAME = "alert.cfg";
    public static final Object[] optionOK = {"OK"};
    public static final int ALERT_DEBUG_MODE = 0; //Default dont show debug mode
    public static final int ALERT_TIP = 1;
    public static final int ALERT_SPEECH = 2;
    public static final int ALERT_MUTE = 0;
    public static final int ALERT_BEEP = 1;
    public static final int ALERT_SPEAKER = 2;
//    public static final int ALERT_YM = 3;
//    public static final int ALERT_SMS = 4;
//    public static final int ALERT_PHONE = 5;
//    public static final int ALERT_HOST = 6;
//    public static final int SYSTEM_ERROR = 0;
//    public static final int CDR_ERROR = 1;
//    public static final int DATABASE_ERROR = 2;
//    public static final int APPLICATION_ERROR = 3;
//    public static final int OTHER_ERROR = 4;
//    public static final int WARNING_ERROR = 5;
//    public static final int SEND2CP_ERROR = 6;
    public static final String ROOT_LABEL = "-Providers";
    public static final String GPC_LABEL = "Console - GPC";
    public static final String VMS_LABEL = "Console - VMS";
    public static final String VIETEL_LABEL = "Console - VIETEL";
    public static final String ORTHER_CONSOLE_LABEL = "Console";
    public static final String SYSTEM_LABEL = "Gateway";
    public static final String CDR_LABEL = "CDR";
    public static final String DATABASE_LABEL = "Database";
    public static final String APPLICATION_LABEL = "Application";
    public static final String WARNING_LABEL = "Warning";
    public static final String SEND2CP_LABEL = "Send2CP";
    public static final int DOUBLE_CLICK = 2;
    public static String[] MONITOR_LABELS = {ORTHER_CONSOLE_LABEL, GPC_LABEL, VMS_LABEL, VIETEL_LABEL, SYSTEM_LABEL, DATABASE_LABEL, APPLICATION_LABEL, SEND2CP_LABEL};
    public static String[] MONITOR_CPS = {"Other", "8x77", "8x83", "6x04", "8x71"};
    public static String[] MONITOR_LOGGER = {"Console", "Monitor Client", "Task Scheduler"};
//    public static final String YM_USER = "exmonitor";
//    public static final String YM_PASSWORD = "bluesea8x77";
//    public static final String DBA_USERS = "DANGNP";
    public static final String SMTP_SERVER = "mail.bluesea.vn";
    public static final String EMAIL_USER = "thongbao";
    public static final String EMAIL_PASSWORD = "announce8X";
    public static final String EMAIL_ADDRESS = "thongbao@bluesea.vn";
//    public static final String COMMAND_CODE_CONSOLE = "CONSOLE";
//    public static final String SERVICE_ID_8077 = "8077";
//    public static final String[] NETWORK_OPERATORS = {"GPC", "VMS", "VIETEL", "EVN", "SFONE", "CITY", "HTM"};
    public static final int TREE_ROOT_LEVEL = 0;
    public static final int TREE_PARENT_LEVEL = 1;
    public static final int TREE_CHILD_LEVEL = 2;
    public static final String VOICE_NAME = "kevin16";
    public static int MONITOR_SERVER_PORT = 9200;
    public static int MONITOR_TIME_CHECK = 5000;
    //Monitor type
    public static final int MONITOR_CLIENT_TYPE = 0;
    public static final int MONITOR_SERVER_TYPE = 1;
    //Monitor setting
    public static String RABBIT_USER = "monitor";
    public static String RABBIT_PASSWORD = "monitor";
    public static String RABBIT_VITUALHOST = "/monitor";
    public static String RABBIT_SERVER = "210.211.97.115";
    public static int RABBIT_PORT = 5672;
}
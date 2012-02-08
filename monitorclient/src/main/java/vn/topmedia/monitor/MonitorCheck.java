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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import vn.topmedia.monitor.bean.AddressBean;
import vn.topmedia.monitor.common.MonitorType;
import vn.topmedia.monitor.commons.Constants;

/**
 * Client check server application and report to Monitor Server
 *
 * @author Anh Tuan <tuanta@topmedia.vn>
 */
public class MonitorCheck extends Thread {

    private MonitorServer logger = new MonitorServer(MonitorCheck.class);
    private static int SOCKET_TIMEOUT = 2000;//milis
    private boolean running;
    private AddressBean address;

    public MonitorCheck(AddressBean address) {
        this.address = address;
    }

    private void checkAddress() {
        logger.info("Application", MonitorType.CONSOLE, "Check address " + (address.getHttp().length() > 0 ? address.getHttp() : address.getIp() + " and port " + address.getPort()));
        if (address.getHttp().length() != 0) {
            //2.Check address
            check(address.getHttp());
        } else {
            //1.Ping to address
            if (!ping(address.getIp())) {
                logger.error("Application", MonitorType.MONITOR_CLIENT, "Ping not reach to " + address.getIp());
            }
            //2.Check address
            check(address.getIp(), address.getPort());
        }
    }

    @Override
    public void run() {
        running = true;
        while (running) {
            checkAddress();
            try {
                MonitorCheck.sleep(Constants.MONITOR_TIME_CHECK);
            } catch (InterruptedException ex) {
            }
        }
    }

    public void close() {
        running = false;
    }

    /**
     * Ping address.
     *
     * @param ip
     * @return
     */
    public boolean ping(String ip) {
        String command = "ping " + ip;
        try {
            Process p = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                if (s.equals("")) {
                    continue;
                }
                if (s.indexOf("timed out") > 0 || s.indexOf("unreachable") > 0) {
                    return false;
                }
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Check address by ip and port
     *
     * @param ip
     * @param port
     * @return
     */
    public boolean check(String ip, int port) {
        boolean value = false;
        Socket client = null;
        try {
            client = new Socket(ip, port);
            client.setSoTimeout(SOCKET_TIMEOUT);
            if (client.isConnected()) {
                value = true;
            }
            client.close();
        } catch (IOException ioe) {
            logger.error("Application", MonitorType.MONITOR_CLIENT, "Not connect to " + address.getIp() + " and port " + address.getPort() + ". Error " + ioe.toString());
        } finally {
            try {
                client.close();
            } catch (Exception ex) {
            }
        }
        return value;
    }

    /**
     * Check avaiable url.
     *
     * @param url
     * @return
     */
    public boolean check(String url) {
        boolean value = false;
        try {
            URL link = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) link.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // OK.
                value = true;
            }
            connection.disconnect();
        } catch (IOException ioe) {
            logger.error("Application", MonitorType.MONITOR_CLIENT, "Not connect to " + address.getHttp() + ". Error " + ioe.toString());
        } finally {
        }
        return value;
    }

    /**
     * Get ip from url.
     *
     * @param url
     * @return
     */
    private String getIP(String url) {
        BufferedReader in = null;
        try {
            URL whatismyip = new URL(url);
            in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
            return in.readLine(); //you get the IP as a String
        } catch (IOException ex) {
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            }
        }
        return "";
    }
}

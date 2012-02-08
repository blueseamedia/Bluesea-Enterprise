/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.topmedia.monitor.bean;

import java.util.Properties;

/**
 *
 * @author tuanta
 */
public class AddressBean {

    private String link;
    private String http;
    private String ip;
    private int port;

    public AddressBean(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getHttp() {
        return http;
    }

    public void setHttp(String http) {
        this.http = http;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

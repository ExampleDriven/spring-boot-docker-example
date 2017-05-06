package org.exampledriven.docker.echo.domain;

/**
 * Created by Peter_Szanto on 6/16/2016.
 */
public class HostInfo {

    private String address;
    private int port;
    private String hostName;

    public HostInfo() {
    }

    public HostInfo(String address, int port, String hostName) {
        this.address = address;
        this.port = port;
        this.hostName = hostName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}

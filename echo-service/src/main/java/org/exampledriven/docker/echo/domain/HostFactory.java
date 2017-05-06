package org.exampledriven.docker.echo.domain;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Peter_Szanto on 3/2/2017.
 */
public class HostFactory {

    public static HostInfo create(HttpServletRequest request) {
        return new HostInfo(request.getLocalAddr(), request.getLocalPort(), getHostName());
    }

    private static String getHostName() {
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        return addr.getHostName();
    }

}

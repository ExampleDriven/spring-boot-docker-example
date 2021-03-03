package org.exampledriven.docker.echo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Peter_Szanto on 6/16/2016.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HostInfo {

    private String address;
    private int port;
    private String hostName;

}

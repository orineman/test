package com.infowave.pda.kernel.hostConnector.concurrent;

import com.infowave.pda.kernel.hostConnector.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 14.03.2008
 * Time: 0:32:03
 * Paccage : com.infowave.pda.kernel.hostConnector.concurrent
 */
public class ConcurrentHostConnector extends HostConnector{
    

    /**
     * Creates a new instance of HostConnector
     */
    public ConcurrentHostConnector(HttpServletRequest request, String protocolPath) {

        super(request);
    }


}

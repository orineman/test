package com.infowave.pda.kernel.actions.concurrent;

import com.infowave.c4p.model.CannotConnectToICMException;
import com.infowave.pda.kernel.actions.misc.Parameter;
import com.infowave.pda.kernel.hostConnector.HostConnector;
import com.infowave.pda.kernel.hostConnector.errors.CannotFindDesktop;
import com.infowave.pda.kernel.hostConnector.errors.CouldNotFindProtocolJSP;
import com.infowave.pda.kernel.hostConnector.errors.InvalidHostResponseXML;
import com.infowave.pda.kernel.hostConnector.errors.InvalidProtocolData;
import org.w3c.dom.Document;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;
import java.util.concurrent.Callable;

/**
 * Created by Alexey.Ogarkov.
 * User: Alexey.Ogarkov
 * Date: 11.03.2008
 * Time: 2:46:59
 * Paccage : com.infowave.pda.kernel.actions
 */
public class RequestCall implements Callable<Parameter<String, Document>> {
    Parameter<String,Document> parameter;
    HostConnector connector;
    HttpServletRequest request;
    String protocolURL;

    public RequestCall( String protocolURL,String name,HostConnector connector) {
        this.connector = connector;
        this.protocolURL = protocolURL;
        parameter = new Parameter<String,Document>();
        parameter.setName(name);
    }

    public Parameter<String,Document> call() throws Exception {
        try {
            parameter.setValue(connector.send(protocolURL));
        } catch (CouldNotFindProtocolJSP couldNotFindProtocolJSP) {
            couldNotFindProtocolJSP.printStackTrace();
        } catch (InvalidProtocolData invalidProtocolData) {
            invalidProtocolData.printStackTrace();
        } catch (CannotConnectToICMException e) {
            e.printStackTrace();
        } catch (InvalidHostResponseXML invalidHostResponseXML) {
            invalidHostResponseXML.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (CannotFindDesktop cannotFindDesktop) {
            cannotFindDesktop.printStackTrace();
        } finally {
        }
        if (connector.getError() != null) {
            parameter.setError(connector.getError());
        }

        return parameter;
    }
}

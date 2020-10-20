/**
 * 
 */
package com.automic.hpqc.actions;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.utils.ConsoleWriter;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author sumitsamson
 * 
 *         The class will clear the user session on HPQC server and user would be logged out.
 */
public class LogoutAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(LogoutAction.class);

    @Override
    public void executeSpecific() throws AutomicException {
        client.resource(baseUrl).path("qcbin").path("authentication-point").path("logout")
                .accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
        ConsoleWriter.writeln("You have been logged out from the system!!!");
        LOGGER.info("Session closed successfully.....");
    }
}

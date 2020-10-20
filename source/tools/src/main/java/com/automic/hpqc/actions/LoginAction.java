/**
 * 
 */
package com.automic.hpqc.actions;

import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.model.Credentials;
import com.automic.hpqc.utils.CommonUtil;
import com.automic.hpqc.utils.ConsoleWriter;
import com.automic.hpqc.utils.Validator;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author sumitsamson
 * 
 *         This class help authenticate the user trying to login to HPQC and if successful, generates authentication
 *         token which can be further used for other operations.
 */
public class LoginAction extends AbstractHttpAction {

    private static final Logger LOGGER = LogManager.getLogger(LoginAction.class);

    private static final String USERNAME_PATTERN = "[a-zA-Z0-9_]*";
    private String userName;
    private String passWord;

    public LoginAction() {
        addOption("username", true, "Username for HPQC");
        addOption("password", true, "Password for HPQC");
    }

    /**
     * This method validate and initialize input
     * 
     * @throws AutomicException
     */
    private void prepareInput() throws AutomicException {
        // initialize variables
        userName = getOptionValue("username");
        HPQCValidator.checkNotEmpty(userName, "Username");
        if (!Validator.isValidText(USERNAME_PATTERN, userName)) {
            LOGGER.error(ExceptionConstants.INVALID_USERNAME);
            throw new AutomicException(ExceptionConstants.INVALID_USERNAME);
        }

        passWord = getOptionValue(Constants.PASSWORD);
        HPQCValidator.checkNotEmpty(passWord, "Password");
    }

    @Override
    protected void executeSpecific() throws AutomicException {
        prepareInput();
        Credentials cred = new Credentials(userName, passWord);
        client.resource(baseUrl).path("qcbin").path("authentication-point").path("alm-authenticate")
                .entity(CommonUtil.object2Xml(cred, false), MediaType.TEXT_XML).accept(MediaType.TEXT_XML)
                .post(ClientResponse.class);
        client.resource(baseUrl).path("qcbin").path("rest").path("site-session").accept(MediaType.APPLICATION_XML)
                .post(ClientResponse.class);
        ConsoleWriter.writeln("You have successfully logged in!!!");
    }
}

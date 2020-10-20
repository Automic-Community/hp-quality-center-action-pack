/**
 *
 */
package com.automic.hpqc.actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.HPQCValidator;
import com.automic.hpqc.config.HttpClientConfig;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.exception.AutomicRuntimeException;
import com.automic.hpqc.filter.AuthenticationFilter;
import com.automic.hpqc.filter.GenericResponseFilter;
import com.automic.hpqc.model.AuthenticationToken;
import com.automic.hpqc.utils.CommonUtil;
import com.automic.hpqc.utils.ConsoleWriter;
import com.automic.hpqc.utils.Validator;
import com.sun.jersey.api.client.Client;

/**
 * This class defines the execution of any action.It provides some initializations and validations on common inputs .The
 * child actions will implement its executeSpecific() method as per their own need.
 */
public abstract class AbstractHttpAction extends AbstractAction {
    private static final Logger LOGGER = LogManager.getLogger(AbstractHttpAction.class);

    /**
     * Service end point
     */
    protected String baseUrl;
    protected Client client;

    private AuthenticationToken atToken;

    /**
     * Connection timeout in milliseconds
     */
    private int connectionTimeOut;

    /**
     * Read timeout in milliseconds
     */
    private int readTimeOut;

    public AbstractHttpAction() {
        addOption(Constants.BASE_URL, true, "Base url");
        addOption(Constants.READ_TIMEOUT, true, "Read timeout");
        addOption(Constants.CONNECTION_TIMEOUT, true, "connection timeout");
        addOption(Constants.AUTH_TOKEN, false, "Authentication Token Id");
    }

    /**
     * This method initializes the arguments and calls the execute method.
     * 
     * @throws AutomicException
     *             exception while executing an action
     */
    public final void execute() throws AutomicException {
        try {
            prepareCommonInputs();
            client = HttpClientConfig.getClient(this.connectionTimeOut, this.readTimeOut);
            client.addFilter(new AuthenticationFilter(atToken));
            client.addFilter(new GenericResponseFilter());
            executeSpecific();
            ConsoleWriter.writeln("AUTH_TOKEN::=" + atToken.encrypt());
        } finally {
            if (client != null) {
                client.destroy();
            }
        }
    }

    private void prepareCommonInputs() throws AutomicException {
        this.baseUrl = getOptionValue("baseurl");
        this.connectionTimeOut = CommonUtil.parseStringValue(getOptionValue(Constants.CONNECTION_TIMEOUT),
                Constants.MINUS_ONE);
        this.readTimeOut = CommonUtil.parseStringValue(getOptionValue(Constants.READ_TIMEOUT), Constants.MINUS_ONE);

        try {
            Validator.validateURI(baseUrl);
            HPQCValidator.lessThan(readTimeOut, Constants.ZERO, "Read Timeout");
            HPQCValidator.lessThan(connectionTimeOut, Constants.ZERO, "Connect Timeout");
        } catch (AutomicException e) {
            LOGGER.error(e.getMessage());
            throw e;
        }

        try {
            atToken = AuthenticationToken.createATToken(baseUrl, getOptionValue(Constants.AUTH_TOKEN));
        } catch (AutomicRuntimeException ex) {
            ConsoleWriter.writeln(ExceptionConstants.INVALID_AUTH_TOKEN);
            throw ex;
        }
    }

    /**
     * Method to execute the action.
     * 
     * @throws AutomicException
     */
    protected abstract void executeSpecific() throws AutomicException;

}

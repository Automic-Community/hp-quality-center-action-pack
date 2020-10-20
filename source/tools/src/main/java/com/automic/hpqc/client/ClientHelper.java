/**
 *
 */
package com.automic.hpqc.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.actions.AbstractAction;
import com.automic.hpqc.actions.ActionFactory;
import com.automic.hpqc.cli.Cli;
import com.automic.hpqc.cli.CliOptions;
import com.automic.hpqc.constants.Action;
import com.automic.hpqc.constants.Constants;
import com.automic.hpqc.exception.AutomicException;

/**
 * Helper class to delegate request to specific Action based on input arguments .
 * */
public final class ClientHelper {

    private static final Logger LOGGER = LogManager.getLogger(ClientHelper.class);

    private ClientHelper() {
    }

    /**
     * Method to delegate parameters to an instance of {@link AbstractAction} based on the value of Action parameter.
     *
     * @param map
     *            of options with key as option name and value is option value
     * @throws AutomicException
     */
    public static void executeAction(String[] args) throws AutomicException {
        String action = new Cli(new CliOptions(), args).getOptionValue(Constants.ACTION).toUpperCase();
        LOGGER.info("Execution starts for action [" + action + "]...");
        AbstractAction useraction = ActionFactory.getAction(Action.valueOf(action));
        useraction.executeAction(args);
    }
}

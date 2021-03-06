package com.automic.hpqc.exception.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.automic.hpqc.constants.ExceptionConstants;
import com.automic.hpqc.exception.AutomicException;
import com.automic.hpqc.exception.AutomicRuntimeException;
import com.automic.hpqc.utils.CommonUtil;
import com.automic.hpqc.utils.ConsoleWriter;

/**
 * This class handles some specific cases like connection timeout/unable to connect and return the response code. In
 * addition to this, it also writes the exception message to console.
 */

public final class ExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ExceptionHandler.class);

    private static final int RESPONSE_NOT_OK = 1;
    private static final int RESPONSE_CONNECT_TIMEOUT = 2;

    private static final String ERRORMSG = "Please check the input parameters. For more details refer java logs";
    private static final String CONNECTION_TIMEOUT = "Connection Timeout.";
    private static final String UNABLE_TO_CONNECT = "Unable to connect.";

    private ExceptionHandler() {

    }

    /**
     * This method handles some specific cases like connection timeout/unable to connect and return the response code.
     * In addition to this, it also writes the exception message to console.
     */
    public static int handleException(Exception ex) {
        int responseCode = RESPONSE_NOT_OK;
        String errorMsg;
        Throwable th = ex;
        while (th.getCause() != null) {
            th = th.getCause();
        }
        if (th instanceof AutomicException || th instanceof AutomicRuntimeException) {
            errorMsg = th.getMessage();
        } else {
            LOGGER.error(ExceptionConstants.GENERIC_ERROR_MSG, ex);
            errorMsg = th.getMessage();
            if (th instanceof java.net.ConnectException || th instanceof java.net.UnknownHostException) {
                errorMsg = UNABLE_TO_CONNECT;
            } else if (th instanceof java.net.SocketTimeoutException) {
                errorMsg = CONNECTION_TIMEOUT;
                responseCode = RESPONSE_CONNECT_TIMEOUT;
            }
        }
        ConsoleWriter.writeln(CommonUtil.formatErrorMessage((errorMsg == null) ? "System Error Occured" : errorMsg));
        ConsoleWriter.writeln(CommonUtil.formatErrorMessage(ERRORMSG));
        return responseCode;
    }

}

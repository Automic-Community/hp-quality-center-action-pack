package com.automic.hpqc.qtp.exceptions;

/**
 * This class is used to capture all the exception(s) while communicating using com4j APIs.
 * 
 */
public class OtaException extends Exception {

    private static final long serialVersionUID = 3340717299005083995L;

    public OtaException() {
        super();
    }

    public OtaException(String message) {
        super(message);
    }
}

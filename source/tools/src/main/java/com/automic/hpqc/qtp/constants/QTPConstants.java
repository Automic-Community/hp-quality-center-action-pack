package com.automic.hpqc.qtp.constants;

/**
 * 
 * @author kamalgarg
 *
 */

/**
 * Constants class contains all the constants.Any url that is added into this class must be updated in the HPQCUttility
 * class method updateBaseUrl.
 * 
 */

public final class QTPConstants {

    public static final String PROPERTY = "sun.arch.data.model";
    public static final String SLASH = "/";
    public static final String URL_POSTFIX = "qcbin";
    public static final String LOCAL_HOST = "localhost";
    public static final String STATUS_PASSED = "2-finished";
    public static final String STATUS_FAIL = "1-fail";
    public static final String STATUS_ENV_FAIL = "3-env fail";
    public static final String STATUS_TIMEOUT = "4-timeout";
    public static final String STATUS_MANUAL = "5-manual";
    public static final String STATUS_UNKNOWN = "unknown";
    public static final String ALL = "all";
    public static final String STATUS_FINISHED_PASSED = "FinishedPassed";
    public static final String EXECUTION = "Execution";
    public static final String STOP = "Stop";

    private QTPConstants() {
    }

}

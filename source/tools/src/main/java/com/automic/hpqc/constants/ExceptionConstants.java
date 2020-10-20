/**
 * 
 */
package com.automic.hpqc.constants;

/**
 * @author sumitsamson
 * 
 */
public final class ExceptionConstants {

    public static final String INVALID_USERNAME = "Username not valid";
    
    public static final String ERROR_READING_FILE = "Error occured during reading of file [%s],probably file doesn't exists ";
    
    public static final String GENERIC_ERROR_MSG = "System Error occured";
    
    public static final String INVALID_ARGS = "Improper Args. Possible cause : %s";
    public static final String INVALID_BASE_URL = "Invalid URL [ %s ]";
    public static final String INVALID_AUTH_TOKEN = "Invalid authentication token";
    public static final String INVALID_AUTH_TOKEN_URL = "Invalid input [URL OR Authentication token]";

    public static final String UNABLE_TO_CLOSE_STREAM = "Error while closing stream";
    public static final String UNABLE_TO_FLUSH_STREAM = "Error while flushing stream";
    
    public static final String UNABLE_TO_WRITEFILE = "Error writing file ";
    
    public static final String INVALID_FILE = "File [%s] is invalid. Possibly file does not exist ";
    public static final String INVALID_ATTACHMENT = "Given attachment is invalid ,probably attachment [%s] does not exists";
    public static final String EMPTY_DIRECTORY = "No Files to attach in the given folder [%s] ";

    public static final String INVALID_DEFECT_INFORMATION = "Defect information is not in valid XML format";
    public static final String INVALID_INPUT_PARAMETER = "Invalid value for parameter [%s] : [%s]";
    public static final String SYSTEM_ERROR = "System Error";
    
    private ExceptionConstants() {
    }

}

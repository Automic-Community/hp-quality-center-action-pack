package com.automic.hpqc.constants;

/**
 * @author sumitsamson
 * 
 */

/**
 * Constants class contains all the constants.
 * 
 */
public final class Constants {

    public static final String TYPE_STRING = "String";
    public static final String TYPE_LOOKUPLIST = "LookupList";
    public static final String TYPE_NUMBER = "Number";
    public static final String TYPE_USERLIST = "UsersList";
    public static final String TYPE_DATE = "Date";
    public static final String TYPE_DATE_TIME = "DateTime";
    public static final String TYPE_MEMO = "Memo";
    public static final String TYPE_REFERNCE = "Reference";

    public static final String ATTRIBUTE_LABEL = "Label";
    public static final String ATTRIBUTE_PHYSICAL_NAME = "PhysicalName";
    public static final String ELEMENT_VALUE = "Value";
    public static final String ELEMENT_TYPE = "Type";
    public static final String ELEMENT_LIST_ID = "List-Id";
    public static final String DEV_COMMENTS = "dev-comments";
    public static final String ATTACHEMNT = "attachment";

    //Constants for QTP test set report generation
    public static final String ID = "id";
    public static final String TEST_CONFIG_ID = "test-config-id";
    public static final String TEST_SET_FOLDER = "test-set-folder";
    public static final String PARENT_ID = "parent-id";
    public static final String NAME = "name";
    public static final String ROOT_TESTSET_FOLDER_PARENT_ID = "-1";
    public static final String TEST_INSTANCE = "test-instance";
    public static final String DOUBLE_SLASH = "\\";
    
    public static final int MINUS_TWO = -2;
    public static final int MINUS_ONE = -1;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    
    public static final int HTTP_OK = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_BAD_REQUEST = 400;
    public static final int HTTP_UNAUTHORIZED = 401;
    public static final int HTTP_FORBIDDEN = 403;
    public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
    public static final int HTTP_NOT_FOUND = 404;
    public static final int HTTP_METHOD_NOT_ALLOWED = 405;
    public static final int HTTP_UNSUPPORTED_MEDIA_TYPE = 415;
    public static final int HTTP_NOT_ACCEPTABLE = 406;
    
    // Cli Constants
    public static final String ACTION = "action";
    public static final String PASSWORD = "password";
    public static final String HELP = "help";
    public static final String CONNECTION_TIMEOUT = "connectiontimeout";
    public static final String READ_TIMEOUT = "readtimeout";
    public static final int IO_BUFFER_SIZE = 4 * 1024;
    public static final String BASE_URL = "baseurl";
    public static final String AUTH_TOKEN = "authtokenid";

    private Constants() {
    }

}

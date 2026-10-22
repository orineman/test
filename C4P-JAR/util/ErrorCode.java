package com.infowave.c4p.util;

/**
 * Created by: Vincent Aumont
 * Copyright 2003 Infowave Software, Inc.
 */
public class ErrorCode {
    private static final int DATABASE_ERROR_CODE = -4;
    private static final int SYSTEM_ERROR_CODE = 2000;
    private static final int INVALID_PARAMETERS_CODE = 2001;
    private static final int FORM_ALREADY_SUBMITTED_CODE = 2002;
    private static final int DESKTOP_CONNECTED_CODE = 2003;
    private static final int USER_ALREADY_DEFINED_CODE = 1000;
    private static final int DID_ALREADY_DEFINED_CODE = 1010;
    private static final int UNKNOWN_DID_CODE = 1011;
    private static final int INVALID_USERNAME_CODE = 1012;
    private static final int USERNAME_ALREADY_EXISTS_CODE = 1013;
    private static final int PC_NAME_ALREADY_EXISTS_CODE = 1014;
    private static final int INVALID_PASSWORD_CODE = 1015;
    private static final int INVALID_SECURITY_ANSWER_CODE = 1016;
    private static final int NOT_OWNER_OF_DESKTOP_CODE = 1017;
    private static final int DESKTOP_ALREADY_ATTACHED_CODE = 1018;
    private static final int INVALID_LICENSE_NUMBER_CODE = 1019;
    private static final int INVALID_ID_CODE = 1020;
    private static final int TOO_MANY_LICENSES_CODE = 1021;
    private static final int INVALID_RC_LICENSE_NUMBER_CODE = 1022;
    private static final int LOGIN_ERROR = 1023;
    private static final int ACCESS_RESTRICTED_CODE = 1025;
    private static final int DESKTOP_DISCONNECTED_ERROR = -3;

    private static final int INVALID_SERVER_CERTIFICATE_CODE = 1024;

    private static final int DATA_ENTRY_USERNAME_MISSING_CODE = 3000;
    private static final int DATA_ENTRY_USERNAME_FORMAT_CODE = 3001;

    private static final int DATA_ENTRY_PASSWORD_MISSING_CODE = 3002;
    private static final int DATA_ENTRY_PASSWORD_FORMAT_CODE = 3003;
    private static final int DATA_ENTRY_PASSWORD_CONF_MISSING_CODE = 3004;
    private static final int DATA_ENTRY_EMAIL_MISSING_CODE = 3005;
    private static final int DATA_ENTRY_EMAIL_FORMAT_CODE = 3006;
    private static final int DATA_ENTRY_SEC_ANSWER_MISSING_CODE = 3007;
    private static final int DATA_ENTRY_PASSWORDS_DONT_MATCH_CODE = 3008;
    private static final int DATA_ENTRY_PC_NAME_MISSING_CODE = 3009;
    private static final int DATA_ENTRY_FIRSTNAME_FORMAT_CODE = 3010;
    private static final int DATA_ENTRY_LASTNAME_FORMAT_CODE = 3011;
    private static final int DATA_ENTRY_LICENSE_CODE_FORMAT_CODE = 3012;

    private static final int DATA_ENTRY_PASSWORD_HAS_SPACES_CODE = 3013;


    /**
     * A server error. That error mean that Database problem accurrs
     */
    public static final ErrorCode DATABASE_ERROR =
            new ErrorCode(DATABASE_ERROR_CODE,
                    "DATABASE_CINNECTION_ERROR");

     /**
     * A server error. That error mean that you should install yuor certificate in current Java
     */
    public static final ErrorCode DESKTOP_CONNECTED =
            new ErrorCode(DESKTOP_CONNECTED_CODE,
                    "DESKTOP_CONNECTED_ERROR");

    /**
     * A server error. That error mean that you should install yuor certificate in current Java
     */
    public static final ErrorCode INVALID_SERVER_CERTIFICATE =
            new ErrorCode(INVALID_SERVER_CERTIFICATE_CODE,
                    "CERTIFICATE_ERROR");

    /**
     * A server error. That error mean that you should install yuor certificate in current Java
     */
    public static final ErrorCode ACCESS_RESTRICTED =
            new ErrorCode(ACCESS_RESTRICTED_CODE,
                    "RESTRICTED_ACCESS_ERROR");


    /**
     * A general system error. A database error for instance
     */
    public static final ErrorCode DESKTOP_DISCONNECTED =
            new ErrorCode(DESKTOP_DISCONNECTED_ERROR,
                    "DESKTOP_ERROR");

    /**
     * A general system error. A database error for instance
     */
    public static final ErrorCode SYSTEM_ERROR =
            new ErrorCode(SYSTEM_ERROR_CODE,
                    "SYSTEM_ERROR");

    /**
     * Invalid parameters
     */
    public static final ErrorCode INVALID_PARAMETERS =
            new ErrorCode(INVALID_PARAMETERS_CODE,
                    "INVALID_PARAMETERS");

    /**
     * The user pressed the Submit button twice
     */
    public static final ErrorCode FORM_ALREADY_SUBMITTED =
            new ErrorCode(FORM_ALREADY_SUBMITTED_CODE,
                    "FORM_ALREADY_SUBMITTED");

    /**
     * When trying to register a user with a user name that is already defined
     */
    public static final ErrorCode USER_ALREADY_DEFINED =
            new ErrorCode(USER_ALREADY_DEFINED_CODE,
                    "USER_ALREADY_DEFINED");

    /**
     * When trying to create a Device wirh a did that has already been assigned
     */
    public static final ErrorCode DID_ALREADY_DEFINED =
            new ErrorCode(DID_ALREADY_DEFINED_CODE,
                    "DID_ALREADY_DEFINED");

    /**
     * When atempting to perform an operation on a device identified by an
     * invalid DID.
     */
    public static final ErrorCode UNKNOWN_DID_DEFINED =
            new ErrorCode(UNKNOWN_DID_CODE,
                    "UNKNOWN_DID_DEFINED");

    /**
     * A user name does not exist
     */
    public static final ErrorCode INVALID_USERNAME =
            new ErrorCode(INVALID_USERNAME_CODE,
                    "INVALID_USER_NAME");

    /**
     * The pasword is invalid
     */
    public static final ErrorCode INVALID_PASSWORD =
            new ErrorCode(INVALID_PASSWORD_CODE,
                    "INVALID_PASSWORD");

    /**
     * The scurity answer is invalid
     */
    public static final ErrorCode INVALID_SECURITY_ANSWER =
            new ErrorCode(INVALID_SECURITY_ANSWER_CODE,
                    "INVALID_SECURITY_ANSWER");


    /**
     * This username is already taken
     */
    public static final ErrorCode USERNAME_ALREADY_EXISTS =
            new ErrorCode(USERNAME_ALREADY_EXISTS_CODE,
                    "USERNAME_ALREADY_EXISTS");

    /**
     * This license number dos not exist
     */
    public static final ErrorCode INVALID_LICENSE_NUMBER =
            new ErrorCode(INVALID_LICENSE_NUMBER_CODE,
                    "INVALID_LICENSE_NUMBER");

    /**
     * This license number dos not exist
     */
    public static final ErrorCode INVALID_RC_LICENSE_NUMBER =
            new ErrorCode(INVALID_RC_LICENSE_NUMBER_CODE,
                    "INVALID_RC_LICENSE_NUMBER");

    /**
     * The user is using too many license codes
     */
    public static final ErrorCode TOO_MANY_LICENSES =
            new ErrorCode(TOO_MANY_LICENSES_CODE,
                    "TOO_MANY_LICENSES");


    /**
     * This PC name is already taken
     */
    public static final ErrorCode PC_NAME_ALREADY_EXISTS =
            new ErrorCode(PC_NAME_ALREADY_EXISTS_CODE,
                    "PC_NAME_ALREADY_EXISTS");

    /**
     * Ownership violation
     */
    public static final ErrorCode NOT_OWNER_OF_DESKTOP =
            new ErrorCode(NOT_OWNER_OF_DESKTOP_CODE,
                    "NOT_OWNER_OF_DESKTOP");

    /**
     * Trying to attach a desktop to a user who has already access to it
     */
    public static final ErrorCode DESKTOP_ALREADY_ATTACHED =
            new ErrorCode(DESKTOP_ALREADY_ATTACHED_CODE,
                    "DESKTOP_ALREADY_ATTACHED");

    /**
     * an invalid primary key was passed to the data layer
     */
    public static final ErrorCode INVALID_ID =
            new ErrorCode(INVALID_ID_CODE,
                    "INVALID_ID");

    /**
     * The user did not enter a user name when required
     */
    public static final ErrorCode DATA_ENTRY_USERNAME_MISSING_ERROR =
            new ErrorCode(DATA_ENTRY_USERNAME_MISSING_CODE,
                    "DATA_ENTRY_USERNAME_MISSING");
    /**
     * Wrong format for a username
     */
    public static final ErrorCode DATA_ENTRY_USERNAME_FORMAT_ERROR =
            new ErrorCode(DATA_ENTRY_USERNAME_FORMAT_CODE,
                    "DATA_ENTRY_USERNAME_FORMAT");
    /**
     * Wrong format for a first name name
     */
    public static final ErrorCode DATA_ENTRY_FIRSTNAME_FORMAT_ERROR =
            new ErrorCode(DATA_ENTRY_FIRSTNAME_FORMAT_CODE,
                    "DATA_ENTRY_FIRSTNAME_FORMAT");
    /**
     * Wrong format for a first last name
     */
    public static final ErrorCode DATA_ENTRY_LASTNAME_FORMAT_ERROR =
            new ErrorCode(DATA_ENTRY_LASTNAME_FORMAT_CODE,
                    "DATA_ENTRY_LASTNAME_FORMAT");
    /**
     * The user did not enter a password when required
     */
    public static final ErrorCode DATA_ENTRY_PASSWORD_MISSING_ERROR =
            new ErrorCode(DATA_ENTRY_PASSWORD_MISSING_CODE,
                    "DATA_ENTRY_PASSWORD_MISSING");
    /**
     * Wrong format for a password
     */
    public static final ErrorCode DATA_ENTRY_PASSWORD_FORMAT_ERROR =
            new ErrorCode(DATA_ENTRY_PASSWORD_FORMAT_CODE,
                    "DATA_ENTRY_PASSWORD_FORMAT");
    /**
     * A password has spaces at the beginning or the end.
     */
    public static final ErrorCode DATA_ENTRY_PASSWORD_HAS_SPACES_ERROR =
            new ErrorCode(DATA_ENTRY_PASSWORD_HAS_SPACES_CODE,
                    "DATA_ENTRY_PASSWORD_HAS_SPACES");
    /**
     * The user did not enter an email when required
     */
    public static final ErrorCode DATA_ENTRY_EMAIL_MISSING_ERROR =
            new ErrorCode(DATA_ENTRY_EMAIL_MISSING_CODE,
                    "DATA_ENTRY_EMAIL_MISSING");
    /**
     * Wrong format for an email address
     */
    public static final ErrorCode DATA_ENTRY_EMAIL_FORMAT_ERROR =
            new ErrorCode(DATA_ENTRY_EMAIL_FORMAT_CODE,
                    "DATA_ENTRY_EMAIL_FORMAT");
    /**
     * the user did not enter a security answer when required
     */
    public static final ErrorCode DATA_ENTRY_SEC_ANSWER_MISSING_ERROR =
            new ErrorCode(DATA_ENTRY_SEC_ANSWER_MISSING_CODE,
                    "DATA_ENTRY_SEC_ANSWER_MISSING");

    public static final ErrorCode DATA_ENTRY_PASSWORD_CONF_MISSING_ERROR =
            new ErrorCode(DATA_ENTRY_PASSWORD_CONF_MISSING_CODE,
                    "DATA_ENTRY_PASSWORD_CONF_MISSING");

    public static final ErrorCode DATA_ENTRY_PASSWORDS_DONT_MATCH_ERROR =
            new ErrorCode(DATA_ENTRY_PASSWORDS_DONT_MATCH_CODE,
                    "DATA_ENTRY_PASSWORDS_DONT_MATCH");

    public static final ErrorCode DATA_ENTRY_PC_NAME_MISSING_ERROR =
            new ErrorCode(DATA_ENTRY_PC_NAME_MISSING_CODE,
                    "DATA_ENTRY_PC_NAME_MISSING");

    /**
     * The license code is invalid
     */
    public static final ErrorCode DATA_ENTRY_LICENSE_CODE_FORMAT =
            new ErrorCode(DATA_ENTRY_LICENSE_CODE_FORMAT_CODE,
                    "DATA_ENTRY_LICENSE_CODE_FORMAT");


    private int m_code;
    private String m_name;

    public ErrorCode(int code, String name) {
        m_code = code;
        m_name = name;
    }

    public String getName() {
        return m_name;
    }

    public int toInt() {
        return m_code;
    }

    public String toString() {
        return m_name;
    }

}

package utils.mybatis.services;

public enum ReqAttr {

    ERROR_CODE("status"),
    HTTPS_STATUS("statusHTTP"),
    ERROR_TYPE("error"),
    EXCEPTION_DESCRIPTION("exception"),
    MESSAGE("message"),
    CUSTOM_MSG("customMessage"),
    PATH("path"),

    USER_ID("userId"),
    USER_MSG("userMessage");

    private final String attributeName;

    ReqAttr(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getValue() {
        return attributeName;
    }
}

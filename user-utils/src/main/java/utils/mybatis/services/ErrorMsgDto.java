package utils.mybatis.services;

public class ErrorMsgDto {

    private ErrorMsgDto() {
        //not called
    }

    private static final String CHAR = " characters";

    public static final String MISSING_PARAM_MSG = "[Missing Parameter]";
    public static final String INVALID_FULL_NAME_SIZE = "Parameter 'fullName' should have max " + FieldsSizeDto.USER_NAME_MAX_SIZE + CHAR;
    public static final String INVALID_NIP_SIZE = "Parameter 'userNip' should have max " + FieldsSizeDto.USER_NIP_SIZE + CHAR;
    public static final String INVALID_PESEL_SIZE = "Parameter 'userPesel' should have max " + FieldsSizeDto.USER_ADDRESS_MAX_SIZE + CHAR;
    public static final String INVALID_ADDRESS_SIZE = "Parameter 'address' should have max " + FieldsSizeDto.USER_ADDRESS_MAX_SIZE + CHAR;
    public static final String INVALID_CITY_SIZE = "Parameter 'city' should have max " + FieldsSizeDto.USER_CITY_MAX_SIZE + CHAR;
    public static final String INVALID_NIK_SIZE = "Parameter 'nik' should have max " + FieldsSizeDto.NIK_SIZE + CHAR;

    public static final String INVALID_NBR_SIZE = "Parameter 'accountNbr' should be " + FieldsSizeDto.ACCOUNT_NRB_SIZE + CHAR + " long";

}

package mybatis.services;

public interface IErrorMsg {


    String MISSING_PARAM_MSG = "[Missing Parameter]";
    String INVALID_FULL_NAME_SIZE = "Parameter 'fullName' should have max " + IFieldsSize.USER_NAME_MAX_SIZE + " characters";
    String INVALID_NIP_SIZE = "Parameter 'userNip' should have max " + IFieldsSize.USER_NIP_SIZE + " characters";
    String INVALID_PESEL_SIZE = "Parameter 'userPesel' should have max " + IFieldsSize.USER_ADDRESS_MAX_SIZE + " characters";
    String INVALID_ADDRESS_SIZE = "Parameter 'address' should have max " + IFieldsSize.USER_ADDRESS_MAX_SIZE + " characters";
    String INVALID_CITY_SIZE = "Parameter 'city' should have max " + IFieldsSize.USER_CITY_MAX_SIZE + " characters";

    String INVALID_NBR_SIZE = "Parameter 'accountNbr' should be " + IFieldsSize.ACCOUNT_NRB_SIZE + " characters long";

    String DATA_CONFLICT = "Data integrity violation";

}

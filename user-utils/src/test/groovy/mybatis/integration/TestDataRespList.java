package mybatis.integration;

public final class TestDataRespList {


    public static final String RESP_FIRST =
            "{\"nik\":\"23241122\",\"fullName\":\"Harry Potter\",\"nip\":\"4231235001\"," +
                    "\"pesel\":\"94021200333\",\"address\":\"ul. Karmelicka 12/3, 31-820 Kraków\"}";


    public static final String RESP_SECOND =
            "{\"nik\":\"23241534\",\"fullName\":\"Monika Kowalska\",\"nip\":\"5531719222\"," +
                    "\"pesel\":\"86061254333\",\"address\":\"ul Długa 13, 31-435 Kraków\"}";


    public static final String RESP_EXC_FIRST =
            "{\"status\":\"ConstraintViolationException\",\"statusHTTP\":\"BAD_REQUEST\",\"error\":\"Bad Request\"," +
                    "\"message\":\"[ConstraintViolationImpl{interpolatedMessage='size must be between 6 and 8', " +
                    "propertyPath=getUserByNik.arg0, rootBeanClass=class utils.mybatis.controller.UserController, " +
                    "messageTemplate='{javax.validation.constraints.Size.message}'}]\"";

    public static final String RESP_EXC_SECOND =
            "\"status\":400,\"error\":\"Bad Request\"," +
                    "\"exception\":\"utils.mybatis.error.handler.UserDataNotFoundException\"," +
                    "\"message\":\"Request is INVALID !!!\",\"path\":\"/api/user/getUserByNik\"";

    public static final String GET_USER_BY_NIK = "/api/user/getUserByNik?nik=";
}

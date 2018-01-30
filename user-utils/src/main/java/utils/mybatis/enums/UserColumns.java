package utils.mybatis.enums;

public enum UserColumns {

    NAME("user_name"),
    NIK("nik"),
    PESEL("user_pesel"),
    ADDRESS("user_address"),
    CITY("city");

    private final String userColumnName;

    UserColumns(String userColumnName) {
        this.userColumnName = userColumnName;
    }

    public String getColumnName() {
        return userColumnName;
    }

}

package utils.mybatis.enums;

public enum SortTypes {

    ASC("ASC"),
    DESC("DESC"),
    NULLSLAST("NULLS LAST");

    private final String sortType;

    SortTypes(String sortType) {
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }
}

package utils.mybatis.services;

public final class SortCriteria {

    private SortCriteria() {
        //not called
    }

    public static final String MAX_ITEMS_PER_PAGE = "itemsPerPage";
    public static final String PAGE_NUMBER = "pageNumber";
    public static final String OFFSET = "offset";
    public static final String SORT_BY_COLUMN = "columnName";
    public static final String ORDER_TYPE = "orderType";

    public static final String DEFAULT_COLUMN_NAME = "NAME";

    public static final String SORT_BY_DEFAULT = "user_name";
    public static final String DEFAULT_ORDER_TYPE = "ASC";

}

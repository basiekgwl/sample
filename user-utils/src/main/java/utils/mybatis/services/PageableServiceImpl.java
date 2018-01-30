package utils.mybatis.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import utils.mybatis.interfaces.IPageableService;
import webapi.mybatis.dict.SortTypes;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Validated
@Service
@AllArgsConstructor
public class PageableServiceImpl implements IPageableService {

    public Map<String, Integer> pageNumberSizeAndOffset(Pageable pageable) {

        Map<String, Integer> pageableData = new LinkedHashMap<>();
        pageableData.put(SortCriteria.PAGE_NUMBER, pageable.getPageNumber());
        pageableData.put(SortCriteria.MAX_ITEMS_PER_PAGE, pageable.getPageSize());
        pageableData.put(SortCriteria.OFFSET, pageable.getOffset());
        return pageableData;
    }

    public List<String> sortByColumnAndOrderAllParameters(Pageable pageable, String sortByDefaultColumnName) {

        Sort sortProperties = pageable.getSort();
        List<String> orders = new ArrayList<>();

        if (sortProperties == null) {
            orders = setDefaultOrderIfSortIsNull(sortByDefaultColumnName);
        } else {
            for (Sort.Order order : sortProperties) {
                String columnName = setSortCriteria(order.getProperty(), sortByDefaultColumnName);
                String orderType = setSortCriteria(order.getDirection().name(), SortCriteria.DEFAULT_ORDER_TYPE);
                orders.add(String.format("%s %s", columnName, orderType));
            }
        }
        return orders;
    }

    public Map<String, String> nthSortCriteria(List<String> orderCriteria, int number) {

        Map<String, String> pageableSortData = new LinkedHashMap<>();
        String columnName = orderCriteria.get(number).split(" ")[0];
        String orderType = orderCriteria.get(number).split(" ")[1];

        pageableSortData.put(SortCriteria.SORT_BY_COLUMN, columnName);
        pageableSortData.put(SortCriteria.ORDER_TYPE, orderType);
        return pageableSortData;
    }

    public SortTypes orderTypeEnum(String orderType) {
        return SortTypes.valueOf(orderType);
    }


    private String setSortCriteria(String sortCriteria, String defaultValue) {
        if (sortCriteria == null) {
            sortCriteria = defaultValue;
        }
        return sortCriteria;
    }

    private List<String> setDefaultOrderIfSortIsNull(String defaultColumnName) {
        List<String> orders = new ArrayList<>();
        String orderType = SortCriteria.DEFAULT_ORDER_TYPE;
        orders.add(String.format("%s %s", defaultColumnName, orderType));
        return orders;
    }
}

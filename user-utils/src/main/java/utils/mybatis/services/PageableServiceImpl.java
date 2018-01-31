package utils.mybatis.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import utils.mybatis.interfaces.IPageableService;
import utils.mybatis.enums.SortTypes;

import java.util.List;

@Validated
@Service
@AllArgsConstructor
@Slf4j
public class PageableServiceImpl implements IPageableService {

    public Sort allSortCriteria(Pageable pageable, String sortByDefaultColumnName) {
        Sort sortProperties = pageable.getSort();
        if (sortProperties == null) {
            return setSortCriteriaIfNull(sortByDefaultColumnName);
        } else {
            return sortProperties;
        }
    }

    public Sort.Order returnFirstSortOrder(Sort sortOrders) {

        if (sortOrders.iterator().hasNext()) {
            return sortOrders.iterator().next();
        } else {
            return null;
        }
    }

    public Sort.Order nthSortCriteria(Sort sortOrders, int number) {
        int it = 0;
        for (Sort.Order order : sortOrders) {
            if (it == number) {
                return order;
            } else {
                it++;
            }
        }
        return null;
    }

    public String orderTypeEnumValue(String orderType) {
        SortTypes sortType = SortTypes.valueOf(orderType);
        return returnSortTypeValue(sortType);
    }

    public <T> Page<T> resultList(List<T> rowsOnTheCurrentPage, Pageable pageable, int totalCount, String defaultColName) {
        Sort sortCriteria = pageable.getSort();
        if (sortCriteria == null) {
            sortCriteria = setSortCriteriaIfNull(defaultColName);
        }
        Pageable newPageableData = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), sortCriteria);
        return new PageImpl<>(rowsOnTheCurrentPage, newPageableData, totalCount);
    }

    public RowBounds rowBoundsParam(Pageable pageable) {
        //pageNumber from 0

        int pageNumber = pageable.getPageNumber();
        int itemsPerPage = pageable.getPageSize();
        RowBounds rowbounds = new RowBounds();
        int offset = pageNumber * itemsPerPage;
        if (pageNumber > -1) {
            rowbounds = new RowBounds(offset, itemsPerPage);
        }
        return rowbounds;
    }

    public String returnSortTypeValue(SortTypes sortType) {
        return sortType.getSortType();
    }

    private String setSortCriteria(String sortCriteria, String defaultValue) {
        return (sortCriteria == null) ? defaultValue : sortCriteria;
    }

    private Sort setSortCriteriaIfNull(String sortByDefaultColumnName) {
        return new Sort(new Sort.Order(Sort.Direction.ASC, sortByDefaultColumnName));
    }
}

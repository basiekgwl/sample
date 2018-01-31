package utils.mybatis.interfaces;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import utils.mybatis.enums.SortTypes;

import java.util.List;

public interface IPageableService {

    Sort allSortCriteria(Pageable pageable, String sortByDefaultColumnName);

    String orderTypeEnumValue(String orderType);

    Sort.Order nthSortCriteria(Sort sortOrders, int number);

    Sort.Order returnFirstSortOrder(Sort sortOrders);

    String returnSortTypeValue(SortTypes sortType);

    <T> Page<T> resultList(List<T> rowsOnTheCurrentPage, Pageable pageable, int totalCount, String defaultColName);

    RowBounds rowBoundsParam(Pageable pageable);
}

package utils.mybatis.interfaces;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import utils.mybatis.enums.SortTypes;

import java.util.List;
import java.util.function.Function;

public interface IPageableService {

    @FunctionalInterface
    interface TriConsumer<T1, T2, T3> {
        T1 apply(T2 arg2, T3 arg3);
    }

    <D> Page<D> selectAllUsersFromPage(Pageable pageable, int totalCount, String defaultColName, TriConsumer<List<D>, Sort.Order, RowBounds> rowsOnCurrentPageDto);

    Sort allSortCriteria(Pageable pageable, String sortByDefaultColumnName);

    String orderTypeEnumValue(String orderType);

    Sort.Order nthSortCriteria(Sort sortOrders, int number);

    Sort.Order returnFirstSortOrder(Sort sortOrders);

    String returnSortTypeValue(SortTypes sortType);

    <T> Page<T> resultList(List<T> rowsOnTheCurrentPage, Pageable pageable, int totalCount, String defaultColName);

    RowBounds rowBoundsParam(Pageable pageable);

    <D, E> List<D> entityListToDtoList(List<E> currentPageRows, Function<E, D> myLambda);
}

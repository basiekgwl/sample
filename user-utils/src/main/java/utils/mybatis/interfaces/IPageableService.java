package utils.mybatis.interfaces;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import utils.mybatis.enums.SortTypes;

import java.util.List;
import java.util.Map;

public interface IPageableService {

    Map<String, Integer> pageNumberSizeAndOffset(Pageable pageable);

    List<String> sortByColumnAndOrderAllParameters(Pageable pageable, String sortByDefaultColumnName);

    String orderTypeEnumValue(String orderType);

    Map<String, String> nthSortCriteria(List<String> orderCriteria, int number);

    String returnSortTypeValue(SortTypes sortType);

    <T> Page<T> resultList(List<T> rowsOnTheCurrentPage, Pageable pageable, int totalCount, String defaultColName);

    RowBounds rowBoundsParam(int pageNumber, int itemsPerPage);
}

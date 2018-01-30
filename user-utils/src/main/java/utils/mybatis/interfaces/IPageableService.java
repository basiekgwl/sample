package utils.mybatis.interfaces;

import org.springframework.data.domain.Pageable;
import webapi.mybatis.dict.SortTypes;

import java.util.List;
import java.util.Map;

public interface IPageableService {

    Map<String, Integer> pageNumberSizeAndOffset(Pageable pageable);
    List<String> sortByColumnAndOrderAllParameters(Pageable pageable, String sortByDefaultColumnName) ;
    SortTypes orderTypeEnum(String orderType);
    Map<String, String> nthSortCriteria(List<String> orderCriteria, int number);
}

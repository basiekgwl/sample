package utils.mybatis.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class SortListParameters {

    private String columnName;
    private String sortType;

}

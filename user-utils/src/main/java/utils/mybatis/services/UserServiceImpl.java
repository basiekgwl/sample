package utils.mybatis.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import utils.mybatis.dao.UserEntity;
import utils.mybatis.dto.mappers.UserDtoMapper;
import utils.mybatis.error.handler.UserDataNotFoundException;
import utils.mybatis.interfaces.UserService;
import utils.mybatis.mapper.UserDbMapper;
import utils.mybatis.enums.UserColumns;
import webapi.mybatis.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Validated
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDbMapper userDbMapper;
    private final PageableServiceImpl pageableService;

    private static final String DEF_COL_NAME = SortCriteria.DEFAULT_COLUMN_NAME;
    private static final String PAGE_NUMBER = SortCriteria.PAGE_NUMBER;
    private static final String MAX_ITEMS_PER_PAGE = SortCriteria.MAX_ITEMS_PER_PAGE;
    private static final String SORT_BY_COLUMN = SortCriteria.SORT_BY_COLUMN;
    private static final String ORDER_TYPE = SortCriteria.ORDER_TYPE;

    public Page<UserDto> selectAllUsersFromPage(Pageable pageable) {

        Map<String, Integer> sortProperty = pageableService.pageNumberSizeAndOffset(pageable);
        int pageNo = sortProperty.get(PAGE_NUMBER);
        int itemsSize = sortProperty.get(MAX_ITEMS_PER_PAGE);

        List<String> orders = pageableService.sortByColumnAndOrderAllParameters(pageable, DEF_COL_NAME);
        Map<String, String> sortCriteria = pageableService.nthSortCriteria(orders, 0);

        String columnName = sortCriteria.get(SORT_BY_COLUMN);
        String orderType = sortCriteria.get(ORDER_TYPE);

        String orderTypeEnumValue = pageableService.orderTypeEnumValue(orderType);
        List<UserDto> userList = selectAllUsers(pageNo, itemsSize, columnName, orderTypeEnumValue);

        return pageableService.resultList(userList, pageable, userCount(), DEF_COL_NAME);
    }

    public int userCount() {
        return userDbMapper.countAll();
    }

    private List<UserDto> selectAllUsers(int pageNumber, int size, String columnName, String orderType) {

        String selectByColumn = UserColumns.valueOf(columnName).getColumnName();
        log.info("Sort by column: " + selectByColumn);

        RowBounds rowBoundsParam = pageableService.rowBoundsParam(pageNumber, size);

        List<UserEntity> currentPageRows = userDbMapper.selectAllUsers(selectByColumn, orderType, rowBoundsParam);
        return returnListMap(currentPageRows);
    }

    public List<UserDto> getUserListByName(String fullName) {

        List<UserEntity> results = userDbMapper.findByUserFullName(fullName);

        if (results == null) {
            log.error(CommonErrorMsg.MSG_IF_NULL + " NAME:" + fullName);
            throw new UserDataNotFoundException();
        }
        return returnListMap(results);
    }

    private List<UserDto> returnListMap(List<UserEntity> currentPageRows) {
        List<UserDto> userListDto = new ArrayList<>();
        for (UserEntity userEntity : currentPageRows) {
            UserDto userDto = UserDtoMapper.mapUserEntityToDto(userEntity);
            log.info("BEFORE - EntityData: UserData: " + userEntity);
            log.info("AFTER: DTO Data: " + userDto);
            userListDto.add(userDto);
        }
        return userListDto;
    }

}

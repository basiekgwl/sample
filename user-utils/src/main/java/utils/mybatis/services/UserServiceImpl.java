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
import webapi.mybatis.dict.SortTypes;
import webapi.mybatis.dict.UserColumns;
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

    public Page<UserDto> selectAllUsersFromPage(Pageable pageable) {

        int pageNo = pageableService.pageNumberSizeAndOffset(pageable).get(SortCriteria.PAGE_NUMBER);
        int itemsSize = pageableService.pageNumberSizeAndOffset(pageable).get(SortCriteria.MAX_ITEMS_PER_PAGE);

        List<String> orders = pageableService.sortByColumnAndOrderAllParameters(pageable, SortCriteria.DEFAULT_COLUMN_NAME);
        Map<String, String> sortCriteria = pageableService.nthSortCriteria(orders, 0);

        String columnName = sortCriteria.get(SortCriteria.SORT_BY_COLUMN);
        String orderType = sortCriteria.get(SortCriteria.ORDER_TYPE);

        SortTypes sort = pageableService.orderTypeEnum(orderType);
        List<UserDto> userList = selectAllUsers(pageNo, itemsSize, columnName, sort);

        //pageable = new PageRequest(pageNo-1, itemsSize, sortProperties);
        //return new PageImpl<>(userList, pageable, userCount());

        return pageableService.resultList(userList, pageable, userCount());

    }

    public int userCount() {
        return userDbMapper.countAll();
    }

    private List<UserDto> selectAllUsers(int pageNumber, int size, String columnName, SortTypes sortType) {
        UserColumns colName = UserColumns.valueOf(columnName);
        String selectByColumn;
        if (columnName != null) {
            selectByColumn = colName.getColumnName();
        } else {
            selectByColumn = SortCriteria.SORT_BY_DEFAULT;
        }
        log.info("Sort by column: " + selectByColumn);

        String sortByColumnName = pageableService.returnSortTypeValue(sortType);
        RowBounds rowBoundsParam = pageableService.rowBoundsParam(pageNumber, size);

        List<UserEntity> currentPageRows = userDbMapper.selectAllUsers(selectByColumn, sortByColumnName, rowBoundsParam);
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

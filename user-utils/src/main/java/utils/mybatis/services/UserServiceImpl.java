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

@Slf4j
@Validated
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDbMapper userDbMapper;
    private final PageableServiceImpl pageableService;

    private static final String DEFAULT_COLUMN_NAME = SortCriteria.DEFAULT_COLUMN_NAME;

    public Page<UserDto> selectAllUsersFromPage(Pageable pageable) {

        RowBounds rowBoundsParam = pageableService.rowBoundsParam(pageable);
        Sort sortCriteria = pageableService.allSortCriteria(pageable, DEFAULT_COLUMN_NAME);
        Sort.Order orderType = pageableService.returnFirstSortOrder(sortCriteria);
        
        List<UserDto> userList = selectAllUsers(orderType.getProperty(), orderType.getDirection().name(), rowBoundsParam);
        return pageableService.resultList(userList, pageable, userCount(), DEFAULT_COLUMN_NAME);
    }

    public int userCount() {
        return userDbMapper.countAll();
    }

    private List<UserDto> selectAllUsers(String columnName, String orderType, RowBounds rowBoundsParam) {

        String selectByColumn = UserColumns.valueOf(columnName).getColumnName();
        log.info("Sort by column: " + selectByColumn);

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

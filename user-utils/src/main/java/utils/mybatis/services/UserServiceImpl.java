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
import utils.mybatis.interfaces.IPageableService;
import utils.mybatis.interfaces.UserService;
import utils.mybatis.mapper.UserDbMapper;
import utils.mybatis.enums.UserColumns;
import webapi.mybatis.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Slf4j
@Validated
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDbMapper userDbMapper;
    private final PageableServiceImpl pageableService;

    private static final String DEFAULT_COLUMN_NAME = SortCriteria.DEFAULT_COLUMN_NAME;


    public Page<UserDto> selectAllUsersFromPage(Pageable pageable) {

        IPageableService.TriConsumer<List<UserDto>, Sort.Order, RowBounds> userDtoList = this::selectAllUsers;
        return pageableService.selectAllUsersFromPage(pageable, userCount(), DEFAULT_COLUMN_NAME, userDtoList);
    }

//    public Page<UserDto> selectAllUsersFromPage(Pageable pageable) {
//        RowBounds rowBoundsParam = pageableService.rowBoundsParam(pageable);
//        Sort sortCriteria = pageableService.allSortCriteria(pageable, DEFAULT_COLUMN_NAME);
//        Sort.Order orderType = pageableService.returnFirstSortOrder(sortCriteria);
//        List<UserDto> userList = selectAllUsers(orderType.getProperty(), orderType.getDirection().name(), rowBoundsParam);
//        return pageableService.resultList(userList, pageable, userCount(), DEFAULT_COLUMN_NAME);
//    }

    public int userCount() {
        return userDbMapper.countAll();
    }

    private List<UserDto> selectAllUsers(Sort.Order orderCriteria, RowBounds rowBoundsParam) {

        String selectByColumn = UserColumns.valueOf(orderCriteria.getProperty()).getColumnName();
        SortListParameters sortData = new SortListParameters(selectByColumn, orderCriteria.getDirection().name());

        List<UserEntity> currentPageRows = userDbMapper.selectAllUsers(sortData, rowBoundsParam);
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
        Function<UserEntity, UserDto> getNameLambda = UserDtoMapper::mapUserEntityToDto;
        return pageableService.entityListToDtoList(currentPageRows, getNameLambda);
    }
}

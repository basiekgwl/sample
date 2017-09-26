package mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import mybatis.dto.mappers.NewUserDtoMapper;
import mybatis.error.handler.UserDataNotFoundException;
import mybatis.services.CommonErrorMsg;
import webapi.mybatis.api.INewUserController;
import mybatis.dao.UserEntity;
import mybatis.dto.UserDto;
import mybatis.dto.mappers.UserDtoMapper;
import mybatis.mapper.EmployeeDBMapper;
import mybatis.services.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import webapi.mybatis.dto.NewUserDto;
import webapi.mybatis.dto.NewUserWithAccountsDto;

@Slf4j
@RestController
@Validated // This means that this class is a Controller
public class NewUserController implements INewUserController {

    @Autowired
    private EmployeeDBMapper employeeDBMapper;

    @Override
    public NewUserDto getUserByNik(String nik) {
        return NewUserDtoMapper.mapNewUserEntityToDto(employeeDBMapper.findById(nik));
    }

    @Override
    public String deleteUserWithAccounts(String nik) {
        employeeDBMapper.deleteUserWithAccounts(nik);
        return UserMsg.DELETE_USER_DATA_SUCCESS + " UserId: " + nik;
    }

    @Override
    public String updateUser(String fullName, String userNip, String userPesel, String address, String city, String nik) {
        UserEntity myUserEntity = new UserEntity(fullName, userNip, userPesel, address, city, nik);
        employeeDBMapper.updateUserData(myUserEntity);

        UserDto userDto = UserDtoMapper.mapUserEntityToDto(myUserEntity);
        return UserMsg.UPDATE_USER_SUCCESS + " User NIK: " + userDto.getNik();
    }

    @Override
    public NewUserWithAccountsDto getUserEntityWithAllAccounts(String nik) {
        log.info("NIK: " + nik);

        UserEntity userEntityData = employeeDBMapper.getAllAccountsForUserById(nik);

        if (userEntityData == null) {
            log.debug(CommonErrorMsg.MSG_IF_NULL);
            throw new UserDataNotFoundException();
        }

        log.info("Data: " + userEntityData);
        log.info("AccountsData: " + userEntityData.getUserAccounts());

        return NewUserDtoMapper.mapNewUserWithAccountsEntityToDto(userEntityData);
    }

}

package mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import mybatis.controller.interfaces.IUserController;
import mybatis.dao.UserAccountEntity;
import mybatis.dao.UserEntity;
import mybatis.dto.AccountsWithUserDto;
import mybatis.dto.UserDto;
import mybatis.dto.mappers.AccountDtoMapper;
import mybatis.dto.mappers.UserDtoMapper;
import mybatis.error.handler.UserDataNotFoundException;
import mybatis.mapper.EmployeeDBMapper;
import com.viscomp.services.ErrorMsg;
import mybatis.services.CommonErrorMsg;
import mybatis.services.UserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Validated // This means that this class is a Controller
public class UserController extends AbstractController implements IUserController {


    @Autowired
    private EmployeeDBMapper employeeDBMapper;


    //Entity - DELETE USER with all accounts
    @Override
    public String deleteUserWithAccounts(String nik) {
        employeeDBMapper.deleteUserWithAccounts(nik);
        return UserMsg.DELETE_USER_DATA_SUCCESS + " UserId: " + nik;
    }

    //DTO
    public UserDto getUserByNik(String nik) {
        UserEntity user = employeeDBMapper.findById(nik);
        UserDto userDto = UserDtoMapper.mapUserEntityToDto(user);

        log.debug("Get data: " + userDto);
        return userDto;
    }

    public List<UserDto> getUserListByName(String fullName) {

        List<UserDto> userListDto = new ArrayList<>();
        List<UserEntity> results = employeeDBMapper.findByUserFullName(fullName);
        for (UserEntity userEntity : results) {
            UserDto userDto = UserDtoMapper.mapUserEntityToDto(userEntity);
            log.debug("BEFORE - EntityData: UserData: " + userEntity);
            log.debug("AFTER: DTO Data: ");
            userListDto.add(userDto);
        }
        return userListDto;
    }

    public String addNewUser(String fullName, String userNip, String userPesel, String address, String city, String nik) {

        UserEntity myUserEntity = UserEntity.builder()
                .userFullName(fullName)
                .userNip(userNip)
                .userPesel(userPesel)
                .userAddress(address)
                .city(city)
                .nik(nik)
                .build();

        employeeDBMapper.insertNewUser(myUserEntity);

        UserDto userDto = UserDtoMapper.mapUserEntityToDto(myUserEntity);
        return UserMsg.INSERT_USER_SUCCESS + "NIK: " + userDto.getNik();
    }

    public String updateUser(String fullName, String userNip, String userPesel, String address, String city, String nik) {

        UserEntity myUserEntity = new UserEntity(fullName, userNip, userPesel, address, city, nik);
        employeeDBMapper.updateUserData(myUserEntity);

        UserDto userDto = UserDtoMapper.mapUserEntityToDto(myUserEntity);
        return UserMsg.UPDATE_USER_SUCCESS + " User NIK: " + userDto.getNik();
    }

    public AccountsWithUserDto getOneAccountAndUserData(String accountNrb) {

        UserAccountEntity userAccountEntity = employeeDBMapper.getAccountAndUserData(accountNrb);

        log.info("User entity:  " + userAccountEntity);
        if (userAccountEntity == null) {

            log.error(CommonErrorMsg.MSG_IF_NULL);
            throw new UserDataNotFoundException();
        }

        return AccountDtoMapper.mapAccountWithUserDto(userAccountEntity);
    }

    @Override
    public ModelMap handleBadRequests(HttpServletRequest req, ConstraintViolationException ex) {
        return super.handleBadRequests(req, ex);
    }

    @Override
    public ModelMap handleTypeMismatch(HttpServletRequest req, MethodArgumentTypeMismatchException ex) {
        return super.handleTypeMismatch(req, ex);
    }

    @Override
    public ModelMap handleMissingParam(HttpServletRequest req, MissingServletRequestParameterException ex) {
        return super.handleMissingParam(req, ex);
    }

    @Override
    public void conflict() {
        super.conflict();
    }

}

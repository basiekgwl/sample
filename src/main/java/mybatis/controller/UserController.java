package mybatis.controller;

import hello.AccountType;
import lombok.extern.slf4j.Slf4j;
import mybatis.controller.interfaces.IAccountController;
import mybatis.controller.interfaces.IUserController;
import mybatis.dao.UserAccountEntity;
import mybatis.dao.UserEntity;
import mybatis.dto.AccountDto;
import mybatis.dto.AccountsWithUserDto;
import mybatis.dto.UserDto;
import mybatis.dto.UserWithAccountsDto;
import mybatis.dto.mappers.AccountDtoMapper;
import mybatis.dto.mappers.UserDtoMapper;
import mybatis.error.handler.OperationException;
import mybatis.error.handler.UserDataNotFoundException;
import mybatis.mapper.EmployeeDBMapper;
import mybatis.services.IErrorMsg;
import mybatis.services.IUserMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static mybatis.services.ICustomJsonResponses.getJsonResponseForInsert;

@Slf4j
@RestController
@Validated // This means that this class is a Controller
public class UserController extends AbstractController implements IUserController, IAccountController {


    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private EmployeeDBMapper employeeDBMapper;

    //ENTITY - temp only for training
    public List<UserAccountEntity> getAllAccounts() {
        return employeeDBMapper.getAllAccounts();
    }

    public UserAccountEntity getAccountData(String nrb) {
        return employeeDBMapper.getAccountByNumber(nrb);
    }

    //Entity - DELETE USER with all accounts
    @Override
    public String deleteUserWithAccounts(String nik) {
        employeeDBMapper.deleteUserWithAccounts(nik);
        return IUserMsg.DELETE_USER_DATA_SUCCESS + " UserId: " + nik;
    }

    //DTO
    public UserDto getUserByNik(String nik) {
        UserEntity user = employeeDBMapper.findById(nik);
        UserDto userDto = UserDtoMapper.mapUserData(user);

        log.debug("Get data: " + userDto);
        return userDto;
    }

    public List<UserDto> getUserListByName(String fullName) {

        List<UserDto> userListDto = new ArrayList<>();
        List<UserEntity> results = employeeDBMapper.findByUserFullName(fullName);
        for (UserEntity userEntity : results) {
            UserDto userDto = UserDtoMapper.mapUserData(userEntity);
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

        UserDto userDto = UserDtoMapper.mapUserData(myUserEntity);
        return IUserMsg.INSERT_USER_SUCCESS + "NIK: " + userDto.getNik();
    }

    public String updateUser(String fullName, String userNip, String userPesel, String address, String city, String nik) {

        UserEntity myUserEntity = new UserEntity(fullName, userNip, userPesel, address, city, nik);
        employeeDBMapper.updateUserData(myUserEntity);

        UserDto userDto = UserDtoMapper.mapUserData(myUserEntity);
        return IUserMsg.UPDATE_USER_SUCCESS + " User NIK: " + userDto.getNik();
    }

    public UserWithAccountsDto getUserEntityWithAllAccounts(String nik) {

        log.info("NIK: " + nik);

        UserEntity userEntityData = employeeDBMapper.getAllAccountsForUserById(nik);

        if (userEntityData == null) {
            log.debug(IErrorMsg.MSG_IF_NULL);
            throw new UserDataNotFoundException();
        }

        log.info("Data: " + userEntityData);
        log.info("AccountsData: " + userEntityData.getUserAccounts());

        return UserDtoMapper.returnUserWithAccounts(userEntityData);
    }

    public AccountsWithUserDto getOneAccountAndUserData(String accountNrb) {

        UserAccountEntity userAccountEntity = employeeDBMapper.getAccountAndUserData(accountNrb);

        log.info("User entity:  " + userAccountEntity);
        if (userAccountEntity == null) {

            log.error(IErrorMsg.MSG_IF_NULL);
            throw new UserDataNotFoundException();
        }

        return AccountDtoMapper.mapAccountWithUserDto(userAccountEntity);
    }

    public List<AccountDto> getUserAccounts(String nik) {

        List<AccountDto> listAccount = new ArrayList<>();
        List<UserAccountEntity> userAccountEntities = employeeDBMapper.findAccountsForNik(nik);

        userAccountEntities.forEach(userAccountEntity ->
                listAccount.add(AccountDtoMapper.mapAccountDto(userAccountEntity)));

        return listAccount;
    }

    public AccountDto insertNrbData(Long id, String nrb, AccountType type, BigDecimal balance) {

        UserAccountEntity myAccountEntity = UserAccountEntity.builder()
                .userId(id)
                .accountType(type)
                .accountNrb(nrb)
                .balance(balance)
                .build();

        employeeDBMapper.insertAccountData(myAccountEntity);

        return AccountDtoMapper.mapAccountDto(myAccountEntity);
    }

    public ModelMap insertNrb(Long userId, AccountType type, String nrb, BigDecimal balance) {
        UserAccountEntity accountData = new UserAccountEntity(userId, type, nrb, balance);
        long result = employeeDBMapper.insertNewAccount(accountData);
        if (result != 1) {
            log.error("Insert failed!");
            throw new OperationException();
        }

        AccountDto accountDto = AccountDtoMapper.mapAccountDto(accountData);
        Integer nrbHashCode = accountDto.getNrb().hashCode();
        return getJsonResponseForInsert(nrbHashCode);
    }

    @Override
    public String updateAccount(Long id, Long userId, String type, String nrb, String balance, String city) {
        return null;
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

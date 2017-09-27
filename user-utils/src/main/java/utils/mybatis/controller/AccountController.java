package utils.mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import utils.mybatis.controller.interfaces.IAccountController;
import utils.mybatis.dao.UserAccountEntity;
import utils.mybatis.dao.UserEntity;
import utils.mybatis.dto.AccountDto;
import utils.mybatis.dto.UserWithAccountsDto;
import utils.mybatis.dto.mappers.AccountDtoMapper;
import utils.mybatis.dto.mappers.UserDtoMapper;
import utils.mybatis.error.handler.OperationException;
import utils.mybatis.error.handler.UserDataNotFoundException;
import utils.mybatis.mapper.UserDbMapper;
import utils.mybatis.services.CustomJsonResponses;
import webapi.mybatis.AccountType;
import utils.mybatis.services.CommonErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Validated // This means that this class is a Controller
public class AccountController extends AbstractController implements IAccountController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserDbMapper userDbMapper;

    @Override
    public List<AccountDto> getAllAccounts() {
        return AccountDtoMapper.mapAccountsList(userDbMapper.getAllAccounts());
    }

    @Override
    public List<AccountDto> getUserAccounts(String nik) {

        List<AccountDto> listAccount = new ArrayList<>();
        List<UserAccountEntity> userAccountEntities = userDbMapper.findAccountsForNik(nik);

        userAccountEntities.forEach(userAccountEntity ->
                listAccount.add(AccountDtoMapper.mapAccountDto(userAccountEntity)));

        return listAccount;
    }

    //ENTITY - temp only for training
    public AccountDto getAccountData(String nrb) {
        return AccountDtoMapper.mapAccountDto(userDbMapper.getAccountByNumber(nrb));
    }

    public UserWithAccountsDto getUserEntityWithAllAccounts(String nik) {

        log.info("NIK: " + nik);

        UserEntity userEntityData = userDbMapper.getAllAccountsForUserById(nik);

        if (userEntityData == null) {
            log.debug(CommonErrorMsg.MSG_IF_NULL);
            throw new UserDataNotFoundException();
        }

        log.info("Data: " + userEntityData);
        log.info("AccountsData: " + userEntityData.getUserAccounts());

        return UserDtoMapper.mapUserWithAccounts(userEntityData);
    }

    @Override
    public AccountDto insertNrbData(Long id, String nrb, AccountType type, BigDecimal balance) {

        UserAccountEntity myAccountEntity = UserAccountEntity.builder()
                .userId(id)
                .accountType(type)
                .accountNrb(nrb)
                .balance(balance)
                .build();

        userDbMapper.insertAccountData(myAccountEntity);

        return AccountDtoMapper.mapAccountDto(myAccountEntity);
    }

    public ModelMap insertNrb(Long userId, AccountType type, String nrb, BigDecimal balance) {
        UserAccountEntity accountData = new UserAccountEntity(userId, type, nrb, balance);
        long result = userDbMapper.insertNewAccount(accountData);
        if (result != 1) {
            log.error("Insert failed!");
            throw new OperationException();
        }

        AccountDto accountDto = AccountDtoMapper.mapAccountDto(accountData);
        Integer nrbHashCode = accountDto.getNrb().hashCode();
        return CustomJsonResponses.getJsonResponseForInsert(nrbHashCode);
    }

    @Override
    public String updateAccount(Long id, Long userId, String type, String nrb, String balance, String city) {
        return null;
    }
}
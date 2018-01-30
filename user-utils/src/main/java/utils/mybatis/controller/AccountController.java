package utils.mybatis.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import webapi.mybatis.api.IAccountController;
import utils.mybatis.dao.UserAccountEntity;
import utils.mybatis.dao.UserEntity;
import webapi.mybatis.dto.AccountDto;
import webapi.mybatis.dto.UserWithAccountsDto;
import utils.mybatis.dto.mappers.AccountDtoMapper;
import utils.mybatis.dto.mappers.UserDtoMapper;
import utils.mybatis.error.handler.OperationException;
import utils.mybatis.error.handler.UserDataNotFoundException;
import utils.mybatis.mapper.UserDbMapper;
import utils.mybatis.services.CustomJsonResponses;
import webapi.mybatis.dict.AccountType;
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
@AllArgsConstructor
@Validated // This means that this class is a Controller
public class AccountController extends AbstractController implements IAccountController {

    private final UserDbMapper userDbMapper;

    @Override
    public List<AccountDto> getAllAccounts() {

        List<UserAccountEntity> userAccountEntities = userDbMapper.getAllAccounts();
        if (userAccountEntities == null) {
            log.error(CommonErrorMsg.MSG_IF_NULL + " ALL ACCOUNTS IN DB");
            throw new UserDataNotFoundException();
        }
        return AccountDtoMapper.mapAccountsList(userAccountEntities);
    }

    @Override
    public List<AccountDto> getUserAccounts(String nik) {

        List<AccountDto> listAccount = new ArrayList<>();
        List<UserAccountEntity> userAccountEntities = userDbMapper.findAccountsForNik(nik);
        if (userAccountEntities == null) {
            log.error(CommonErrorMsg.MSG_IF_NULL + " NIK:" + nik);
            throw new UserDataNotFoundException();
        }
        userAccountEntities.forEach(userAccountEntity ->
                listAccount.add(AccountDtoMapper.mapAccountDto(userAccountEntity)));
        return listAccount;
    }

    //ENTITY - temp only for training
    public AccountDto getAccountData(String nrb) {

        UserAccountEntity userAccountEntity = userDbMapper.getAccountByNumber(nrb);
        if (userAccountEntity == null) {
            log.error(CommonErrorMsg.MSG_IF_NULL + " NRB:" + nrb);
            throw new UserDataNotFoundException();
        }
        return AccountDtoMapper.mapAccountDto(userAccountEntity);
    }

    public UserWithAccountsDto getUserEntityWithAllAccounts(String nik) {

        log.info("NIK: " + nik);
        UserEntity userEntityData = userDbMapper.getAllAccountsForUserById(nik);
        if (userEntityData == null) {
            log.debug(CommonErrorMsg.MSG_IF_NULL + " NIK:" + nik);
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
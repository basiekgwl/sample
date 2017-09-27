package utils.mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import utils.mybatis.dao.UserAccountEntity;
import utils.mybatis.dto.mappers.NewAccountDtoMapper;
import utils.mybatis.error.handler.UserDataNotFoundException;
import utils.mybatis.mapper.UserDbMapper;
import utils.mybatis.services.CommonErrorMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import webapi.mybatis.AccountType;
import webapi.mybatis.api.INewAccountController;
import webapi.mybatis.dto.NewAccountDto;
import webapi.mybatis.dto.NewAccountWithUserDto;
import webapi.mybatis.dto.NewUserWithAccountsDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Validated // This means that this class is a Controller
public class NewAccountController implements INewAccountController{

    @Autowired
    UserDbMapper userDbMapper;

    @Override
    public List<NewAccountDto> getAllAccounts() {
        return NewAccountDtoMapper.returnAllAccountsListDto(userDbMapper.getAllAccounts());
    }

    @Override
    public List<NewAccountDto> getUserAccounts(String nik) {
        List<NewAccountDto> listAccount = new ArrayList<>();
        List<UserAccountEntity> userAccountEntities = userDbMapper.findAccountsForNik(nik);

        userAccountEntities.forEach(userAccountEntity ->
                listAccount.add(NewAccountDtoMapper.mapAccountEntityToDto(userAccountEntity)));

        return listAccount;
    }

    @Override
    public NewAccountDto getAccountData(String nrb) {
        return null;
    }

    @Override
    public NewUserWithAccountsDto getUserEntityWithAllAccounts(String nik) {
        return null;
    }

    @Override
    public ModelMap insertNrb(Long userId, AccountType type, String nrb, BigDecimal balance) {
        return null;
    }

    @Override
    public NewAccountDto insertNrbData(Long id, String nrb, AccountType type, BigDecimal balance) {
        return null;
    }

    @Override
    public String updateAccount(Long id, Long userId, String type, String nrb, String balance, String city) {
        return null;
    }

    @Override
    public NewAccountWithUserDto getOneAccountAndUserData(String accountNrb) {
        UserAccountEntity userAccountEntity = userDbMapper.getAccountAndUserData(accountNrb);

        log.info("User entity:  " + userAccountEntity);
        if (userAccountEntity == null) {

            log.error(CommonErrorMsg.MSG_IF_NULL);
            throw new UserDataNotFoundException();
        }

        return NewAccountDtoMapper.mapAccountWithUserDto(userAccountEntity);
    }
}

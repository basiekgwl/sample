package mybatis.controller;

import hello.AccountType;
import lombok.extern.slf4j.Slf4j;
import mybatis.dao.UserAccountEntity;
import mybatis.dto.AccountDto;
import mybatis.dto.mappers.AccountDtoMapper;
import mybatis.dto.mappers.NewAccountDtoMapper;
import mybatis.dto.mappers.NewUserDtoMapper;
import mybatis.mapper.EmployeeDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import webapi.mybatis.api.INewAccountController;
import webapi.mybatis.dto.NewAccountDto;
import webapi.mybatis.dto.NewUserWithAccountsDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@Validated // This means that this class is a Controller
public class NewAccountController implements INewAccountController{

    @Autowired
    private EmployeeDBMapper employeeDBMapper;

    @Override
    public List<NewAccountDto> getAllAccounts() {
        return NewUserDtoMapper.returnAllAccountsListDto(employeeDBMapper.getAllAccounts());
    }

    @Override
    public List<NewAccountDto> getUserAccounts(String nik) {
        List<NewAccountDto> listAccount = new ArrayList<>();
        List<UserAccountEntity> userAccountEntities = employeeDBMapper.findAccountsForNik(nik);

        userAccountEntities.forEach(userAccountEntity ->
                listAccount.add(NewUserDtoMapper.mapAccountEntityToDto(userAccountEntity)));

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
    public NewAccountDto insertNrbData(Long id, String nrb, AccountType type, BigDecimal balance) {
        return null;
    }

    @Override
    public ModelMap insertNrb(Long userId, AccountType type, String nrb, BigDecimal balance) {
        return null;
    }

    @Override
    public String updateAccount(Long id, Long userId, String type, String nrb, String balance, String city) {
        return null;
    }
}

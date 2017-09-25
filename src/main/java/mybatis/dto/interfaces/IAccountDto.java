package mybatis.dto.interfaces;

import mybatis.dao.UserAccountEntity;
import mybatis.dto.AccountDto;
import mybatis.dto.AccountsWithUserDataDto;

public interface IAccountDto {

    AccountDto returnAccountDto(UserAccountEntity userAccountEntity);

    AccountsWithUserDataDto returnAccountWithUserDto(UserAccountEntity userAccountEntity);

    Integer returnHashCode(String nrb);
}

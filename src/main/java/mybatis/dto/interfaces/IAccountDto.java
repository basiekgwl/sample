package mybatis.dto.interfaces;

import mybatis.dao.UserAccountEntity;
import mybatis.dto.AccountDto;

public interface IAccountDto {

    AccountDto returnAccountDto(UserAccountEntity userAccountEntity);

    AccountDto returnAccountWithUserDto(UserAccountEntity userAccountEntity);

    Integer returnHashCode(String nrb);
}

package mybatis.dto.interfaces;

import mybatis.dao.UserAccountEntity;
import mybatis.dto.AccountDto;
import mybatis.dto.AccountsWithUserDto;

public interface IAccountDto {

    AccountDto returnAccountDto(UserAccountEntity userAccountEntity);


    Integer returnHashCode(String nrb);
}

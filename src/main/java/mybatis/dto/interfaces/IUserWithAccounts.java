package mybatis.dto.interfaces;

import mybatis.dao.UserAccountEntity;
import mybatis.dao.UserEntity;
import mybatis.dto.AccountDto;
import mybatis.dto.UserWithAccountsDto;

import java.util.List;

public interface IUserWithAccounts {

    List<AccountDto> returnAccountsList(List<UserAccountEntity> userAccountEntities);

    UserWithAccountsDto returnUserWithAccounts(UserEntity userEntity);
}

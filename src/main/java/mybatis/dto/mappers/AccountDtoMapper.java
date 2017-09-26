package mybatis.dto.mappers;

import mybatis.dao.UserAccountEntity;
import mybatis.dto.AccountsWithUserDto;

public interface AccountDtoMapper {

    static AccountsWithUserDto mapAccountWithUserDto(UserAccountEntity userAccountEntity) {


        AccountsWithUserDto userDataDto = new AccountsWithUserDto();

        userDataDto.setNrb(userAccountEntity.getAccountNrb());
        userDataDto.setAccountType(userAccountEntity.getAccountType());
        userDataDto.setBalance(userAccountEntity.getBalance());
        userDataDto.setUserDto(UserDtoMapper.mapUserData(userAccountEntity.getUserEntity()));

        return userDataDto;
    }
}

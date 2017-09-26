package mybatis.dto.mappers;

import mybatis.dao.UserAccountEntity;
import webapi.mybatis.dto.NewAccountWithUserDto;

public interface NewAccountDtoMapper {

    static NewAccountWithUserDto mapAccountWithUserDto(UserAccountEntity userAccountEntity) {

        NewAccountWithUserDto userDataDto = new NewAccountWithUserDto();

        userDataDto.setNrb(userAccountEntity.getAccountNrb());
        userDataDto.setAccountType(userAccountEntity.getAccountType());
        userDataDto.setBalance(userAccountEntity.getBalance());
        userDataDto.setUserDto(NewUserDtoMapper.mapNewUserEntityToDto(userAccountEntity.getUserEntity()));

        return userDataDto;
    }
}

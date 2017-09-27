package utils.mybatis.dto.mappers;

import utils.mybatis.dao.UserAccountEntity;
import webapi.mybatis.dto.NewAccountDto;
import webapi.mybatis.dto.NewAccountWithUserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface NewAccountDtoMapper {

    static NewAccountWithUserDto mapAccountWithUserDto(UserAccountEntity userAccountEntity) {

        NewAccountWithUserDto userDataDto = new NewAccountWithUserDto();

        userDataDto.setNrb(userAccountEntity.getAccountNrb());
        userDataDto.setAccountType(userAccountEntity.getAccountType());
        userDataDto.setBalance(userAccountEntity.getBalance());
        userDataDto.setUserDto(NewUserDtoMapper.mapNewUserEntityToDto(userAccountEntity.getUserEntity()));

        return userDataDto;
    }

    static NewAccountDto mapAccountEntityToDto(UserAccountEntity userAccountEntity) {
        return NewAccountDto.builder()
                .nrb(userAccountEntity.getAccountNrb())
                .accountType(userAccountEntity.getAccountType())
                .balance(userAccountEntity.getBalance())
                .build();
    }

    static List<NewAccountDto> returnAllAccountsListDto(List<UserAccountEntity> userAccountEntityList) {

        if (userAccountEntityList == null) {
            return new ArrayList<>();
        }
        return userAccountEntityList.stream().map(NewAccountDtoMapper::mapAccountEntityToDto).collect(Collectors.toList());
    }
}

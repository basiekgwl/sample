package utils.mybatis.dto.mappers;

import utils.mybatis.dto.AccountsWithUserDto;
import utils.mybatis.dao.UserAccountEntity;
import utils.mybatis.dto.AccountDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface AccountDtoMapper {

    static AccountDto mapAccountDto(UserAccountEntity userAccountEntity) {

        return AccountDto.builder()
                .nrb(userAccountEntity.getAccountNrb())
                .accountType(userAccountEntity.getAccountType())
                .balance(userAccountEntity.getBalance())
                .build();
    }

    static AccountsWithUserDto mapAccountWithUserDto(UserAccountEntity userAccountEntity) {

        AccountsWithUserDto userDataDto = new AccountsWithUserDto();

        userDataDto.setNrb(userAccountEntity.getAccountNrb());
        userDataDto.setAccountType(userAccountEntity.getAccountType());
        userDataDto.setBalance(userAccountEntity.getBalance());
        userDataDto.setUserDto(UserDtoMapper.mapUserEntityToDto(userAccountEntity.getUserEntity()));

        return userDataDto;
    }

    static List<AccountDto> mapAccountsList(List<UserAccountEntity> userAccountEntities) {
        if(userAccountEntities == null){
            return new ArrayList<>();
        }
        return userAccountEntities.stream().map(AccountDtoMapper::mapAccountDto).collect(Collectors.toList());
    }
}

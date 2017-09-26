package mybatis.dto.mappers;

import mybatis.dao.UserAccountEntity;
import mybatis.dao.UserEntity;
import mybatis.dto.AccountDto;
import webapi.mybatis.dto.NewAccountDto;
import webapi.mybatis.dto.NewUserDto;
import webapi.mybatis.dto.NewUserWithAccountsDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface NewUserDtoMapper {

    static NewUserDto mapNewUserEntityToDto(UserEntity userEntity) {
        return NewUserDto.builder()
                .fullName(userEntity.getUserFullName())
                .nip(userEntity.getUserNip())
                .pesel(userEntity.getUserPesel())
                .address(userEntity.getUserAddress())
                .nik(userEntity.getNik())
                .build();
    }

    static NewUserWithAccountsDto mapNewUserWithAccountsEntityToDto(UserEntity userEntity) {

        NewUserWithAccountsDto userWithAccountsDto = new NewUserWithAccountsDto();

        userWithAccountsDto.setUserDto(mapNewUserEntityToDto(userEntity));
        userWithAccountsDto.setAccountDtoList(NewUserDtoMapper.returnAllAccountsListDto(userEntity.getUserAccounts()));
        return userWithAccountsDto;
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
        return userAccountEntityList.stream().map(NewUserDtoMapper::mapAccountEntityToDto).collect(Collectors.toList());
    }

}

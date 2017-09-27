package utils.mybatis.dto.mappers;

import utils.mybatis.dao.UserEntity;
import webapi.mybatis.dto.NewUserDto;
import webapi.mybatis.dto.NewUserWithAccountsDto;

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
        userWithAccountsDto.setAccountDtoList(NewAccountDtoMapper.returnAllAccountsListDto(userEntity.getUserAccounts()));
        return userWithAccountsDto;
    }

}

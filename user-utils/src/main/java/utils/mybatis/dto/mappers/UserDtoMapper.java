package utils.mybatis.dto.mappers;

import webapi.mybatis.dto.UserDto;
import utils.mybatis.dao.UserEntity;
import webapi.mybatis.dto.UserWithAccountsDto;

public interface UserDtoMapper {

    static UserDto mapUserEntityToDto(UserEntity userEntity) {

        return UserDto.builder()
                .fullName(userEntity.getUserFullName())
                .nip(userEntity.getUserNip())
                .pesel(userEntity.getUserPesel())
                .address(userEntity.getUserAddress())
                .nik(userEntity.getNik())
                .build();
    }

    static UserWithAccountsDto mapUserWithAccounts(UserEntity userEntity) {
        UserWithAccountsDto newUser = new UserWithAccountsDto();
        newUser.setUserDto(UserDtoMapper.mapUserEntityToDto(userEntity));
        newUser.setAccountDtoList(AccountDtoMapper.mapAccountsList(userEntity.getUserAccounts()));
        return newUser;
    }
}

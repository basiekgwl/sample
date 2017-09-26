package mybatis.dto.mappers;

import mybatis.dao.UserEntity;
import mybatis.dto.UserDto;
import mybatis.dto.UserWithAccountsDto;

public interface UserDtoMapper {


    static UserDto mapUserData(UserEntity userEntity) {

        return UserDto.builder()
                .fullName(userEntity.getUserFullName())
                .nip(userEntity.getUserNip())
                .pesel(userEntity.getUserPesel())
                .address(userEntity.getUserAddress())
                .nik(userEntity.getNik())
                .build();
    }

    static UserWithAccountsDto returnUserWithAccounts(UserEntity userEntity) {
        UserWithAccountsDto newUser = new UserWithAccountsDto();
        newUser.setUserDto(UserDtoMapper.mapUserData(userEntity));
        newUser.setAccountDtoList(AccountDtoMapper.returnAccountsList(userEntity.getUserAccounts()));
        return newUser;
    }
}

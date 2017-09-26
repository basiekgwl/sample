package mybatis.dto.mappers;

import mybatis.dao.UserEntity;
import mybatis.dto.UserDto;

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
}

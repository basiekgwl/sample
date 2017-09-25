package mybatis.dto.interfaces;

import mybatis.dao.UserEntity;
import mybatis.dto.UserDto;

public interface IUserDto {

    UserDto returnUserData(UserEntity userEntity);

}

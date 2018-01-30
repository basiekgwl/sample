package utils.mybatis.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import webapi.mybatis.dto.UserDto;

import java.util.List;

public interface UserService {

    Page<UserDto> selectAllUsersFromPage(Pageable pageable);
    List<UserDto> getUserListByName(String fullName);
    int userCount();
}

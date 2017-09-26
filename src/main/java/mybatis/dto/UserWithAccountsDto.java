package mybatis.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import mybatis.dto.interfaces.IUserWithAccounts;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class UserWithAccountsDto implements IUserWithAccounts {

    UserDto userDto;
    List<AccountDto> accountDtoList;

}

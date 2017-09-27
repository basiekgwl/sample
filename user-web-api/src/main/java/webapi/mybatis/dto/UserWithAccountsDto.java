package webapi.mybatis.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithAccountsDto {

    UserDto userDto;
    List<AccountDto> accountDtoList;

}

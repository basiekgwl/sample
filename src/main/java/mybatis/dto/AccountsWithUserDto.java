package mybatis.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsWithUserDto extends AccountDto {

    private UserDto userDto;

}

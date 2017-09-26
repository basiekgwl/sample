package mybatis.dto;

import lombok.*;
import mybatis.dto.interfaces.IAccountDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsWithUserDto extends AccountDto implements IAccountDto {

    private UserDto userDto;

}

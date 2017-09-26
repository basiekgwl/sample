package mybatis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mybatis.dto.interfaces.IAccountDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsWithUserDto extends AccountDto implements IAccountDto {

    private UserDto userDto;

}

package mybatis.dto;

import lombok.*;
import mybatis.dao.UserAccountEntity;
import mybatis.dto.interfaces.IAccountDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsWithUserDataDto extends AccountDto implements IAccountDto {

    private UserDto userDto;

    public AccountsWithUserDataDto returnAccountWithUserDto(UserAccountEntity userAccountEntity) {

        UserDto myNewUserDto = new UserDto();  //?????

        AccountsWithUserDataDto userDataDto = new AccountsWithUserDataDto();

        userDataDto.setNrb(userAccountEntity.getAccountNrb());
        userDataDto.setAccountType(userAccountEntity.getAccountType());
        userDataDto.setBalance(userAccountEntity.getBalance());
        userDataDto.setUserDto(myNewUserDto.returnUserData(userAccountEntity.getUserEntity()));

        return userDataDto;
    }

}

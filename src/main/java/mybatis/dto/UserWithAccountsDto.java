package mybatis.dto;

import lombok.*;
import mybatis.dao.UserAccountEntity;
import mybatis.dao.UserEntity;
import mybatis.dto.interfaces.IUserWithAccounts;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithAccountsDto implements IUserWithAccounts{

    UserDto userDto;
    List<AccountDto> accountDtoList;

    public List<AccountDto> returnAccountsList(List<UserAccountEntity> userAccountEntities) {

        List<AccountDto> newAccountList = new ArrayList<>();
        userAccountEntities.forEach(item ->
                {
                    AccountDto accountDto = new AccountDto();
                    newAccountList.add(accountDto.returnAccountDto(item));
                }
        );
        return newAccountList;
    }

    public UserWithAccountsDto returnUserWithAccounts(UserEntity userEntity) {
        UserWithAccountsDto newUser = new UserWithAccountsDto();
        UserDto userDto = new UserDto();
        newUser.setUserDto(userDto.returnUserData(userEntity));
        newUser.setAccountDtoList(returnAccountsList(userEntity.getUserAccounts()));

        return newUser;
    }



}

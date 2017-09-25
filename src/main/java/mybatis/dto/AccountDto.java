package mybatis.dto;

import hello.AccountType;
import lombok.*;
import mybatis.dao.UserAccountEntity;
import mybatis.dao.UserEntity;
import mybatis.dto.interfaces.IAccountDto;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto implements IAccountDto {

    private String nrb;
    private AccountType accountType;
    private BigDecimal balance;
    private UserDto user;

    public AccountDto returnAccountDto(UserAccountEntity userAccountEntity) {

        UserDto userDto = new UserDto();

        return AccountDto.builder()
                .nrb(userAccountEntity.getAccountNrb())
                .accountType(userAccountEntity.getAccountType())
                .balance(userAccountEntity.getBalance())
                .user(userDto.returnUserData(userAccountEntity.getUserEntity()))
                .build();
    }

    public AccountDto returnAccountWithUserDto(UserAccountEntity userAccountEntity) {

        UserDto newUser = new UserDto();

        return AccountDto.builder()
                .nrb(userAccountEntity.getAccountNrb())
                .accountType(userAccountEntity.getAccountType())
                .balance(userAccountEntity.getBalance())
                .user(newUser.returnUserData(userAccountEntity.getUserEntity()))
                .build();
    }

    public Integer returnHashCode(String nrb) {
        return nrb.hashCode();
    }
}

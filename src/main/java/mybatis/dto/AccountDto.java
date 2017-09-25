package mybatis.dto;

import hello.AccountType;
import lombok.*;
import mybatis.dao.UserAccountEntity;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {

    private String nrb;
    private AccountType accountType;
    private BigDecimal balance;

    public AccountDto returnAccountDto(UserAccountEntity userAccountEntity) {

        return AccountDto.builder()
                .nrb(userAccountEntity.getAccountNrb())
                .accountType(userAccountEntity.getAccountType())
                .balance(userAccountEntity.getBalance())
                .build();
    }

    public Integer returnHashCode(String nrb) {
        return nrb.hashCode();
    }
}

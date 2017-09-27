package utils.mybatis.dao;

import lombok.*;
import webapi.mybatis.dict.AccountType;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountEntity {

    private Long accountId;

    private Long userId;

    private AccountType accountType;

    private String accountNrb;

    private BigDecimal balance;

    private UserEntity userEntity;

    @Builder
    public UserAccountEntity(Long userId, AccountType accountType, String accountNrb, BigDecimal balance) {
        this.userId = userId;
        this.accountType = accountType;
        this.accountNrb = accountNrb;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[accountID =%d, userId=%d, Type='%s', accountNrb='%s', balance= '%s']",
                getAccountId(), getUserId(), getAccountType(), getAccountNrb(), getBalance());
    }
}

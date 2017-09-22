package mybatis.dao;

import hello.AccountType;
import lombok.*;
import mybatis.dao.UserEntity;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccounts {

    private int accountId;

    private int userId;

    private AccountType accountType;

    private String accountNrb;

    private BigDecimal balance;

    private UserEntity userEntity;

    @Builder
    public UserAccounts(int userId, AccountType accountType, String accountNrb, BigDecimal balance) {
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

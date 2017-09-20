package hello;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

import java.math.BigDecimal;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAccounts {

    private int accountId;

    private int userId;

    private AccountType accountType;

    private String accountNrb;

    private BigDecimal balance;

    private User userData;

    public UserAccounts() {
    }

    public UserAccounts(int accountId, int userId, AccountType accountType, String accountNrb, BigDecimal balance) {
        this.accountId = accountId;
        this.userId = userId;
        this.accountType = accountType;
        this.accountNrb = accountNrb;
        this.balance = balance;
    }

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

package mybatis.dto;

import hello.AccountType;
import lombok.*;

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

}

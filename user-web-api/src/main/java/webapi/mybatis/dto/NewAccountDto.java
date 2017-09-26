package webapi.mybatis.dto;

import hello.AccountType;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewAccountDto {

    private String nrb;
    private AccountType accountType;
    private BigDecimal balance;

}

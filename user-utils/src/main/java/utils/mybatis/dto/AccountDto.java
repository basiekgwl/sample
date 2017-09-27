package utils.mybatis.dto;

import lombok.*;
import webapi.mybatis.AccountType;

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

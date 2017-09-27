package webapi.mybatis.dto;

import lombok.*;
import webapi.mybatis.dict.AccountType;

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

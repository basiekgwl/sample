package webapi.mybatis.dto;

import lombok.*;
import webapi.mybatis.AccountType;

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

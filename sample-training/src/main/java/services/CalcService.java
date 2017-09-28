package services;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalcService {

    public BigDecimal doSomeNewAction(BigDecimal netto) {

        BigDecimal value = Calculator.returnNettoFvat(netto);

        return value.multiply(BigDecimal.valueOf(0.18))
                .subtract(BigDecimal.valueOf(247.73))
                .setScale(0, BigDecimal.ROUND_HALF_UP);
    }
}

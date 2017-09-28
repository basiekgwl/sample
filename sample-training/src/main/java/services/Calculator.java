package services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@Component
public class Calculator {

    private String message;

    public static int sum(int firstValue, int secondValue) {
        return firstValue + secondValue;
    }

    public static int multiply(int firstValue, int secondValue) {
        return firstValue * secondValue;
    }

    public static BigDecimal returnNettoFvat(BigDecimal base) {
        return (base.add(BigDecimal.valueOf(49.55))).divide(BigDecimal.valueOf(0.82), BigDecimal.ROUND_UP).setScale(2);
    }


    public int summary(int firstValue, int secondValue) {
        return firstValue + secondValue;
    }

}

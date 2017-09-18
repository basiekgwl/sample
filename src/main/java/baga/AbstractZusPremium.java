package baga;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
abstract class AbstractZusPremium{

    private double socialInsurance;
    private double healthBaseInsurance;
    private double labourFund;
}

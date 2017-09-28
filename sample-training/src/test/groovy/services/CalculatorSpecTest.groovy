package services

import spock.lang.Specification


class CalculatorSpecTest extends Specification {


    def "my first test with Mock"() {

        given:
        Calculator calc = Mock()

        when:
        calc.summary(3, 4)

        then:
        1 * calc.summary(3, 4)


        when:
        calc.summary(1, 0.3)

        then:
        thrown(MissingMethodException)


    }
}

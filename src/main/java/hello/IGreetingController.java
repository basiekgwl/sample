package hello;

import baga.Person;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface IGreetingController {
    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    Greeting greeting(String name);

    @RequestMapping("/employee")
    @Valid
    Person employee(String fullName,
                    @RequestParam(value="nip") @NotNull String nip,
                    String pesel,
                    String address);
}

package hello;

import baga.Person;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController implements IGreetingController {
    private static final String TEMPLATE = "Hello, %s!";

    //An AtomicLong is used in applications such as atomically incremented sequence numbers
    private final AtomicLong counter = new AtomicLong();

    @Override
    public Greeting greeting(String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(TEMPLATE, name));
    }

    @Override
    public Person employee(String fullName, String nip, String pesel, String address) {
        return new Person(fullName, nip, pesel, address);
    }
}
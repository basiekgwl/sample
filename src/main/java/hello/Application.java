package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"hello", "baga"})
public class Application implements CommandLineRunner{


    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    @Bean
    public CommandLineRunner demo(UserRepository repository) {
        return (args) -> {

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (User customer : repository.findAll()) {
                log.info(customer.toString());
            }
            log.info(" ... ");

            // fetch an individual customer by ID
            User customer = repository.findOne(1L);
            log.info("Customer found with findOne(1L):");
            log.info("--------------------------------");
            log.info(customer.toString());
            log.info("");


            // fetch customers by last name
            log.info("Customer found with findByLastName('Baga'):");
            log.info("--------------------------------------------");
            for (User employee : repository.findByUserFullName("Ginny Weasley")) {
                log.info(employee.toString());
            }
            log.info("");
        };

    }

    @Override
    public void run(String... args) throws Exception {
    }

}

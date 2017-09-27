package utils.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"utils"})
public class SampleXmlApplication {

    public static void main(String[] args) {
        System.setProperty("spring.config.name", "application-test");
        SpringApplication.run(SampleXmlApplication.class, args);
    }
}

package mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"mybatis"})
public class SampleXmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SampleXmlApplication.class, args);
    }
}

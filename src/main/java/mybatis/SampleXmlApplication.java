package mybatis;

import hello.Application;
import mybatis.mapper.EmployeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"mybatis"})
public class SampleXmlApplication implements CommandLineRunner{

    private static final Logger log = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) throws Exception {
        SpringApplication.run(SampleXmlApplication.class, args);
    }


    private final EmployeeMapper employeeMapper;

    public SampleXmlApplication(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Get data: " + this.employeeMapper.findById(2L));
    }
}

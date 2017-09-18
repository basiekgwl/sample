package mybatis;


import hello.Application;
import hello.User;
import mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.util.List;

public class HowCreateMyBatisMapper {

    private static final Logger log = LoggerFactory.getLogger(Application.class);
    private static final String MYBATIS_CONF = "mybatis-conf.xml";

    public static void main(String[] args) throws Exception {

        Reader reader = Resources.getResourceAsReader(MYBATIS_CONF);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(reader);
        SqlSession session = sessionFactory.openSession();

        long id = 2;

        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        User employee = mapper.findById(id);

        log.info("RESULT: findById(*) >>>>>> ");

        log.info(employee.getUserId() + " - " + employee.getUserFullName() +
                " - " + employee.getUserAddress());


        log.info(" NEXT RESULTS: findByCity(*) >>>>>> ");

        List<User> myList = mapper.findByCity("Kraków");

        for(User item: myList) {
            log.info(item.getUserId() + " - " + item.getUserFullName() +
                    " - " + item.getUserAddress());
        }

    }
}

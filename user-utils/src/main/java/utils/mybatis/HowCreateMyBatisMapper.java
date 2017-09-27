package utils.mybatis;


import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import utils.mybatis.dao.UserEntity;
import utils.mybatis.mapper.UserDbMapper;

import java.io.Reader;
import java.util.List;

@Slf4j
public class HowCreateMyBatisMapper {

    private static final String MYBATIS_CONF = "mybatis-config.xml";

    public static void main(String[] args) throws Exception {

        Reader reader = Resources.getResourceAsReader(MYBATIS_CONF);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = builder.build(reader);
        SqlSession session = sessionFactory.openSession();

        String nik = "23655939";

        UserDbMapper mapper = session.getMapper(UserDbMapper.class);
        UserEntity employee = mapper.findById(nik);

        log.info("RESULT: findById(*) >>>>>> ");

        log.info(employee.getUserId() + " - " + employee.getUserFullName() +
                " - " + employee.getUserAddress());


        log.info(" NEXT RESULTS: findByCity(*) >>>>>> ");

        List<UserEntity> myList = mapper.findByCity("Kraków");

        for (UserEntity item : myList) {
            log.info(item.getUserId() + " - " + item.getUserFullName() +
                    " - " + item.getUserAddress());
        }

    }
}

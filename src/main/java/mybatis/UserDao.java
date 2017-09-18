package mybatis;

import hello.User;
import mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;

public class UserDao {

    public void save(User userData){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        mapper.insertNewUser(userData);
        session.commit();
        session.close();
    }

    public void update(User userData){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        mapper.updateUserData(userData);
        session.commit();
        session.close();
    }
}

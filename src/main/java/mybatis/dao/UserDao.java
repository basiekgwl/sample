package mybatis.dao;

import hello.User;
import mybatis.MyBatisUtil;
import mybatis.mapper.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;

public class UserDao {

    public long save(User userData){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        long userId = mapper.insertNewUser(userData);
        session.commit();
        session.close();
        return userId;
    }

    public void update(User userData){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        mapper.updateUserData(userData);
        session.commit();
        session.close();
    }
}

package baga;

import mybatis.dao.UserEntity;
import mybatis.utils.MyBatisUtil;
import mybatis.mapper.EmployeeDBMapper;
import org.apache.ibatis.session.SqlSession;

public class UserDao {

    public long save(UserEntity userEntityData){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeDBMapper mapper = session.getMapper(EmployeeDBMapper.class);
        long userId = mapper.insertNewUser(userEntityData);
        session.commit();
        session.close();
        return userId;
    }

    public void update(UserEntity userEntityData){
        SqlSession session = MyBatisUtil.getSqlSessionFactory().openSession();
        EmployeeDBMapper mapper = session.getMapper(EmployeeDBMapper.class);
        mapper.updateUserData(userEntityData);
        session.commit();
        session.close();
    }
}

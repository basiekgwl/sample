package mybatis.mapper;

import hello.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeMapper {

//    @Results({
//            @Result(property = "userId", column = "user_id")
//    })

    @ResultMap("UserMap")
    @Select("SELECT * FROM user WHERE user_id = ${id}")
    User findById(@Param("id") long id);

    List<User> findByCity(String city);

    @ResultMap("UserMap")
    @Select("SELECT user_name, user_pesel, user_address FROM user WHERE user_name = ${fullName}")
    List<User> findByUserFullName(@Param("fullName") String fullName);

    @ResultMap("UserMap")
    @Insert("INSERT into user(user_name, user_nip, user_pesel,user_address, city) " +
            "VALUES(#{userFullName}, #{userNip}, #{userPesel}, #{userAddress}, #{city})")
    void insertNewUser(User userData);

    @ResultMap("UserMap")
    @Update("UPDATE user SET user_name=#{userFullName}, user_nip=#{userNip}, user_pesel=#{userPesel}," +
            "user_address=#{userAddress}, city=#{city} WHERE user_id =#{userId}")
    void updateUserData(User userData);
}

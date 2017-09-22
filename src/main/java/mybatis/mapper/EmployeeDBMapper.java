package mybatis.mapper;

import mybatis.dao.UserEntity;
import mybatis.dao.UserAccounts;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EmployeeDBMapper {

//    @Results({
//            @Result(property = "userId", column = "id")
//    })

    @ResultMap("UserMap")
    @Select("SELECT * FROM user WHERE id = ${id}")
    UserEntity findById(@Param("id") long id);

    List<UserEntity> findByCity(String city);

    @ResultMap("UserMap")
    @Select("SELECT * FROM user WHERE user_name = ${fullName}")
    List<UserEntity> findByUserFullName(@Param("fullName") String fullName);

    @ResultMap("UserMap")
    @Insert("INSERT into user(user_name, user_nip, user_pesel,user_address, city) " +
            "VALUES(#{userFullName}, #{userNip}, #{userPesel}, #{userAddress}, #{city})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    long insertNewUser(UserEntity userEntityData);

    @ResultMap("UserMap")
    @Update("UPDATE user SET user_name=#{userFullName}, user_nip=#{userNip}, user_pesel=#{userPesel}," +
            "user_address=#{userAddress}, city=#{city} WHERE id =#{userId}")
    void updateUserData(UserEntity userEntityData);

    @ResultMap("AccountMap")
    @Select("SELECT * FROM user_accounts WHERE id = ${id}")
    List<UserAccounts> findAccountsByUserId(@Param("id") long id);

    @ResultMap("AccountMap")
    List<UserAccounts> getAllAccounts();

    @ResultMap("AccountMap")
    UserAccounts getAccountByNumber(@Param("nrb") String nrb);

    @ResultMap("AccountMap")
    long insertAccountData(UserAccounts accountData);

    @ResultMap("AccountMap")
    @Insert("INSERT into user_accounts(user_id, account_type, account_nrb, balance) " +
            "VALUES(#{userId}, #{accountType}, #{accountNrb}, #{balance})")
    @Options(useGeneratedKeys = true, keyProperty = "accountId")
    long insertNewAccount(UserAccounts account);


    // it doesn't work so far :/ strange ???
    @Select("SELECT user.* FROM user where user.id = #{userId}")
    @Results(value = {
            @Result(property = "userId", column = "id"),
            @Result(property = "userFullName", column = "user_name"),
            @Result(property = "userNip", column = "user_nip"),
            @Result(property = "userAddress", column = "user_address"),
            @Result(property = "userAccounts", javaType = List.class, column = "user_id", many = @Many(select = "getUserAccounts"))
    })
    UserEntity getMyAllUsers(long userId);

    @ResultMap("AccountMap")
    @Select("SELECT usa.* FROM user_accounts as usa WHERE usa.user_id = #{userId}")
    List<UserAccounts> getUserAccounts(long userId);


    // one to many - xml
    UserEntity getAllAccountsForUserById(@Param("userId") long userId);
    // one to one - xml
    UserAccounts getAccountAndUserData(String accountNrb);
}
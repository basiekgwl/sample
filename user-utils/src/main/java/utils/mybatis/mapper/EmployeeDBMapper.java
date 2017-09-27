package utils.mybatis.mapper;

import utils.mybatis.dao.UserEntity;
import utils.mybatis.dao.UserAccountEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.List;

@Mapper
public interface EmployeeDBMapper {


    @ResultMap("UserMap2")
    @Select("SELECT * FROM user WHERE nik = ${nik}")
    UserEntity findById(@Param("nik") String nik);

    List<UserEntity> findByCity(String city);

    @ResultMap("UserMap2")
    @Select("SELECT * FROM user WHERE user_name = ${fullName}")
    List<UserEntity> findByUserFullName(@Param("fullName") String fullName);

    @ResultMap("UserMap2")
    @Insert("INSERT into user(user_name, user_nip, user_pesel,user_address, city, nik) " +
            "VALUES(#{userFullName}, #{userNip}, #{userPesel}, #{userAddress}, #{city}, #{nik})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    long insertNewUser(UserEntity userEntityData);

    @ResultMap("UserMap2")
    void deleteUserWithAccounts(@RequestParam("nik") @NotNull String nik);

    @ResultMap("UserMap2")
    @Update("UPDATE user SET user_name=#{userFullName}, user_nip=#{userNip}, user_pesel=#{userPesel}," +
            "user_address=#{userAddress}, city=#{city} WHERE nik=#{nik}")
    void updateUserData(UserEntity userEntityData);

    @ResultMap("AccountMap2")
    @Select("SELECT user_accounts.*, user.* FROM user_accounts " +
            "inner join user on user.id = user_accounts.user_id " +
            "WHERE user.nik = ${nik}")
    List<UserAccountEntity> findAccountsForNik(@Param("nik") String nik);

    @ResultMap("AccountMap2")
    List<UserAccountEntity> getAllAccounts();

    @ResultMap("AccountMap2")
    UserAccountEntity getAccountByNumber(@Param("nrb") String nrb);

    @ResultMap("AccountMap2")
    long insertAccountData(UserAccountEntity accountData);

    @ResultMap("AccountMap2")
    @Insert("INSERT into user_accounts(user_id, account_type, account_nrb, balance) " +
            "VALUES(#{userId}, #{accountType}, #{accountNrb}, #{balance})")
    @Options(useGeneratedKeys = true, keyProperty = "accountId")
    long insertNewAccount(UserAccountEntity account);


    // one to many - xml
    UserEntity getAllAccountsForUserById(@Param("nik") String nik);

    // one to one - xml
    UserAccountEntity getAccountAndUserData(String accountNrb);
}
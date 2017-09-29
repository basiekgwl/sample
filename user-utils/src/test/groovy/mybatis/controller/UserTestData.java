package mybatis.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import utils.mybatis.dao.UserAccountEntity;
import utils.mybatis.dao.UserEntity;
import webapi.mybatis.dict.AccountType;
import webapi.mybatis.dto.UserDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public final class UserTestData {

    public static final Long USER1_ID = 3L;
    public static final Long USER2_ID = 4L;
    public static final Long USER3_ID = 5L;
    public static final Long USER4_ID = 6L;

    public static UserEntity USER1 = UserEntity.builder()
            .userId(USER1_ID)
            .nik("10101010")
            .userFullName("Harry Potter")
            .userNip("5531232412")
            .userPesel("83072612422")
            .userAddress("ul. Privet Drive 5, London")
            .city("London")
            .build();

    public static UserEntity USER2 = UserEntity.builder()
            .userId(USER2_ID)
            .nik("12121212")
            .userFullName("Hermiona Granger")
            .userNip("2341230023")
            .userPesel("83040212002")
            .userAddress("ul. Pokątna, London")
            .city("London")
            .build();

    public static UserEntity USER3 = UserEntity.builder()
            .userId(USER3_ID)
            .nik("14141414")
            .userFullName("Ginny Weasley")
            .userNip("4321324433")
            .userPesel("85102514233")
            .userAddress("Nora, London")
            .city("London")
            .build();

    public static UserEntity USER4 = UserEntity.builder()
            .userId(USER4_ID)
            .userFullName("Harry Potter")
            .nik("15151515")
            .userNip("")
            .userPesel("")
            .userAddress("ul. Pokątna 12, London")
            .city("London")
            .build();

    public static List<UserEntity> userList1UserEntities() {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(USER1);
        userList.add(USER4);
        return userList;
    }

    public static List<UserEntity> userList2UserEntities() {
        List<UserEntity> userList = new ArrayList<>();
        userList.add(USER3);
        return userList;
    }

    public static boolean assertResult(List<UserEntity> inputData, List<UserDto> result) {

        inputData.forEach(userEntity -> result.forEach((UserDto item) -> {
            if (Objects.equals(item.getNik(), userEntity.getNik())) {
                Assert.assertEquals("fullName", userEntity.getUserFullName(), item.getFullName());
                Assert.assertEquals("nip", userEntity.getUserNip(), item.getNip());
                Assert.assertEquals("pesel", userEntity.getUserPesel(), item.getPesel());
                Assert.assertEquals("address", userEntity.getUserAddress(), item.getAddress());
            }
        }));
        return true;
    }

    protected static final UserAccountEntity USER3_ACCOUNT = UserAccountEntity.builder()
            .accountType(AccountType.DOMESTIC)
            .accountNrb("26200400010001000100011234")
            .balance(BigDecimal.valueOf(1000.00))
            .userId(3L)
            .build();


    protected static UserAccountEntity accountWithUserData(UserAccountEntity accountData, UserEntity userEntity) {
        accountData.setUserEntity(userEntity);
        return accountData;
    }


}

package mybatis.controller

import groovy.util.logging.Slf4j
import utils.mybatis.controller.UserController
import utils.mybatis.dao.UserAccountEntity
import utils.mybatis.dao.UserEntity
import utils.mybatis.error.handler.UserDataNotFoundException
import utils.mybatis.mapper.UserDbMapper
import utils.mybatis.services.UserMsg
import webapi.mybatis.dto.AccountsWithUserDto
import webapi.mybatis.dto.UserDto

@Slf4j
class UserControllerSpecification extends AssertUtils {

    def userDbMapper = Mock(UserDbMapper)

    def userController = [userDbMapper: userDbMapper] as UserController

    private static final Long ID = 7
    private static final String FULL_NAME = "Harry Potter"
    private static final String NIP = "5537172211"
    private static final String PESEL = "83072600333"
    private static final String ADDRESS = "ul. Pokątna 5a, London"
    private static final String NIK = "23241534"
    private static final String CITY = "London"


    def "POS: getUserByNik - NIK exists"() {

        given:
        userDbMapper.findById(_ as String) >>
                UserEntity.builder()
                        .userId(ID)
                        .userFullName(FULL_NAME)
                        .userNip(NIP)
                        .userPesel(PESEL)
                        .userAddress(ADDRESS)
                        .nik(NIK)
                        .city(CITY)
                        .build()

        when:
        def user = userController.getUserByNik(NIK)

        then:
        user.nik == NIK
        user.fullName == FULL_NAME
        user.nip == NIP
        user.pesel == PESEL
        user.address == ADDRESS
    }

    def "NEG: getUserByNik -> findById returns null"() {

        given:
        userDbMapper.findById(_ as String) >> null

        when:
        userController.getUserByNik("12345678")

        then:
        thrown(UserDataNotFoundException)
    }

    def "NEG: getUserByNik -> findById returns RuntimeException"() {

        given:
        userDbMapper.findById(_ as String) >> RuntimeException

        when:
        userController.getUserByNik("12345678")

        then:
        thrown(RuntimeException)
    }

    def "POS: getUserListByName() >> one result"() {

        given:
        String fullName = UserTestData.USER2.userFullName
        List<UserEntity> userEntityArrayList = new ArrayList<>()
        userEntityArrayList.add(UserTestData.USER2)

        and:
        userDbMapper.findByUserFullName(fullName) >> userEntityArrayList

        when:
        List<UserDto> results = userController.getUserListByName(fullName)

        then:
        results.size() == 1
    }

    def "POS: getUserListByName() >> two results"() {

        given:
        String fullName = UserTestData.USER1.userFullName
        List<UserEntity> userEntityArrayList = new ArrayList<>()
        UserEntity user1 = UserTestData.USER1
        UserEntity user2 = UserTestData.USER4
        userEntityArrayList.add(user1)
        userEntityArrayList.add(user2)

        and:
        userDbMapper.findByUserFullName(fullName) >> userEntityArrayList

        when:
        List<UserDto> results = userController.getUserListByName(fullName)

        then:
        results.size() == 2
        assertUserDtoList(userEntityArrayList, results)
    }

    def "NEG: getUserListByName() >> null"() {

        given:
        userDbMapper.findByUserFullName(_ as String) >> null

        when:
        userController.getUserListByName("Lady Baga")

        then:
        thrown(UserDataNotFoundException)
    }

    def "NEG: getUserListByName() >> Runtime Exception"() {

        given:
        userDbMapper.findByUserFullName(_ as String) >> RuntimeException

        when:
        userController.getUserListByName("Lady Baga")

        then:
        thrown(RuntimeException)
    }

    def "POS: findByUserFullName() - various options"() {

        given:
        List<UserEntity> userEntityArrayList1 = UserTestData.userList1UserEntities()
        UserEntity user1 = userEntityArrayList1.get(0)
        UserEntity user2 = userEntityArrayList1.get(1)
        List<UserEntity> userEntityArrayList2 = UserTestData.userList2UserEntities()
        UserEntity user3 = userEntityArrayList2.get(0)

        and:
        userDbMapper.findByUserFullName(_ as String) >> { String name ->
            if (name == user1.userFullName) {
                return userEntityArrayList1
            } else if (name == user2.userFullName) {
                return userEntityArrayList1
            } else if (name == user3.userFullName) {
                return userEntityArrayList2
            }
        }

        when:
        List<UserDto> results = userController.getUserListByName(user1.userFullName)

        then:
        results.size() == 2
        results.get(0).nik == user1.nik
        results.get(1).nik == user2.nik
        UserTestData.assertResult(userEntityArrayList1, results)
        assertUserDtoList(userEntityArrayList1, results)

        when:
        results = userController.getUserListByName(user2.userFullName)

        then:
        results.size() == 2
        results.get(0).fullName == user1.userFullName
        results.get(1).fullName == user2.userFullName
        assertUserDtoList(userEntityArrayList1, results)


        when:
        results = userController.getUserListByName(user3.userFullName)

        then:
        results.size() == 1
        results.get(0).fullName == user3.userFullName
        assertUserDtoList(userEntityArrayList2, results)
    }

    private static final String NAME = "Ginny Weasley" /// temp

    def "POS: findByUserFullName() test"() {

        given:
        List<UserEntity> userEntityArrayList2 = UserTestData.userList2UserEntities()
        UserEntity user3 = userEntityArrayList2.get(0)

        and:
        1 * userDbMapper.findByUserFullName(_ as String) >> {
            String fullName ->
//            assert fullName == NAME
                userEntityArrayList2
        }

        when:
        List<UserDto> results = userController.getUserListByName(user3.userFullName)

        then:
        results.size() == 1
        results.get(0).fullName == user3.userFullName
        assertUserDtoList(userEntityArrayList2, results)
    }

    def "POS: addNewUser() test"() {

        given:
        UserEntity user = UserTestData.USER2
        and:
        userDbMapper.insertNewUser(_ as UserEntity) >> 1

        when:
        String result = userController.addNewUser(user.userFullName, user.userNip, user.userPesel, user.userAddress, user.city, user.nik)

        then:
        result == returnExpectedMessage(UserMsg.INSERT_USER_SUCCESS, user.nik)
    }


    def "POS: getOneAccountAndUserData() test"() {

        given:
        UserAccountEntity userAccountEntity = UserTestData.accountWithUserData(UserTestData.USER3_ACCOUNT, UserTestData.USER3)
        and:
        1 * userDbMapper.getAccountAndUserData(_ as String) >> { userAccountEntity }

        when:
        AccountsWithUserDto result = userController.getOneAccountAndUserData(UserTestData.USER3_ACCOUNT.accountNrb)

        then:
        result.getNrb() == UserTestData.USER3_ACCOUNT.accountNrb
        assertFieldsForAccountsWithUserDto(userAccountEntity, result)
    }

    def "NEG: getOneAccountAndUserData() ->  getAccountAndUserData >> null"() {

        given:
        1 * userDbMapper.getAccountAndUserData(_ as String) >> { null }

        when:
        userController.getOneAccountAndUserData(UserTestData.USER3_ACCOUNT.accountNrb)

        then:
        thrown(UserDataNotFoundException)
    }

    def "NEG: getOneAccountAndUserData() ->  getAccountAndUserData >> RuntimeException"() {

        given:
        1 * userDbMapper.getAccountAndUserData(_ as String) >> { RuntimeException }

        when:
        userController.getOneAccountAndUserData(UserTestData.USER3_ACCOUNT.accountNrb)

        then:
        thrown(RuntimeException)
    }

    void assertUserDtoList(List<UserEntity> inputData, List<UserDto> result) {
        assertItems(inputData, result,
                { expected, item ->
                    assert expected.getNik() == item.getNik()
                    assert expected.getUserFullName() == item.getFullName()
                    assert expected.getUserNip() == item.getNip()
                    assert expected.getUserPesel() == item.getPesel()
                    assert expected.getUserAddress() == item.getAddress()
                }
        )
    }

    void assertFieldsForAccountsWithUserDto(UserAccountEntity expected, AccountsWithUserDto result) {

        assert result.getNrb() == expected.getAccountNrb()
        assert result.getAccountType() == expected.getAccountType()
        assert result.getBalance() == expected.getBalance()

        UserDto userDto = result.getUserDto()
        UserEntity userEntity = expected.getUserEntity()

        assert userDto.nik == userEntity.nik
        assert userDto.fullName == userEntity.userFullName
        assert userDto.nip == userEntity.userNip
        assert userDto.pesel == userEntity.userPesel
        assert userDto.address == userEntity.userAddress
    }
    
    String returnExpectedMessage(String format, String nik) {
        return format + " NIK: " + nik
    }
}

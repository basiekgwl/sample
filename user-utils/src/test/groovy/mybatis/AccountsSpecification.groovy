package mybatis

import groovy.util.logging.Slf4j
import spock.lang.Specification
import webapi.mybatis.dict.AccountType
import webapi.mybatis.dto.AccountDto
import webapi.mybatis.dto.UserDto
import webapi.mybatis.dto.UserWithAccountsDto

@Slf4j
class AccountsSpecification extends Specification {


    def setupSpec() {
        log.info("setupSpec() - Runs once per Specification")
    }

    def setup() {

//        userWithAccountsDto = Stub(UserWithAccountsDto)
//
//        userWithAccountsDto.getUserDto() >> { UserDto userDto ->
//
//            UserDto someUser = userDto.builder()
//                    .fullName("Hermiona Granger")
//                    .pesel("78020412707")
//                    .address("ul. Miodowa 1/23, 31-865 Kraków")
//                    .nik("23241534")
//                    .build()
//
//            log.info("UserData: " + someUser)
//            return someUser
//        }
//
//        userWithAccountsDto.getAccountDtoList() >> {
//            List<AccountDto> accountDtoList ->
//                AccountDto accountDto = new AccountDto()
//                AccountDto accountDto2 = new AccountDto()
//
//                accountDtoList.add(accountDto.builder()
//                        .accountType(AccountType.DOMESTIC)
//                        .nrb("26254111111111113333443322")
//                        .balance(1235)
//                        .build())
//                accountDtoList.add(accountDto2.builder()
//                        .accountType(AccountType.CREDITS)
//                        .nrb("26254111111111110000443322")
//                        .balance(10000)
//                        .build())
//
//                return accountDtoList
//        }

//        log.info("My list: " + userWithAccountsDto.getUserDto())
//        log.info("My list: " + userWithAccountsDto.getAccountDtoList())
//        log.info(userWithAccountsDto.toString())
    }

    def "check results"() {

        given:
        UserDto userDto = new UserDto()
        userDto = userDto.builder()
                .fullName("Hermiona Granger")
                .nip("5539981232")
                .pesel("81030512999")
                .address("ul. Miodowa 1/23, 31-865 Kraków")
                .nik("23241534")
                .build()
        List<AccountDto> accountDtoList = new ArrayList<>();


        accountDtoList.add(AccountDto.builder()
                .accountType(AccountType.DOMESTIC)
                .nrb("26254111111111113333443322")
                .balance(1235)
                .build())
        accountDtoList.add(AccountDto.builder()
                .accountType(AccountType.CREDITS)
                .nrb("26254111111111110000443322")
                .balance(10000)
                .build())
        accountDtoList.add(AccountDto.builder()
                .accountType(AccountType.SAVINGS)
                .nrb("26254111111111110000440001")
                .balance(23987.23)
                .build())

        when:
        UserWithAccountsDto userWithAccountsDto = new UserWithAccountsDto()
        userWithAccountsDto.setUserDto(userDto)
        userWithAccountsDto.setAccountDtoList(accountDtoList)


        then:
        accountDtoList.size() == 3
        accountDtoList.get(0).balance == 1235
        userDto.getFullName() == "Hermiona Granger"
        userDto.getNik() == "23241534"


        userDto.toString() == "UserDTO[nik=23241534, fullName='Hermiona Granger', NIP='5539981232', Pesel= '81030512999', address='ul. Miodowa 1/23, 31-865 Kraków']"
        accountDtoList.get(0).toString() == "AccountDTO[nrb:26254111111111113333443322, accountType:DOMESTIC, balance:1235]"
        accountDtoList.get(1).toString() == "AccountDTO[nrb:26254111111111110000443322, accountType:CREDITS, balance:10000]"
        accountDtoList.get(2).toString() == "AccountDTO[nrb:26254111111111110000440001, accountType:SAVINGS, balance:23987.23]"


        userWithAccountsDto.toString() ==
               "UserDTO[nik=23241534, fullName='Hermiona Granger', NIP='5539981232', Pesel= '81030512999', address='ul. Miodowa 1/23, 31-865 Kraków'] " +
                "[AccountDTO[nrb:26254111111111113333443322, accountType:DOMESTIC, balance:1235], " +
                "AccountDTO[nrb:26254111111111110000443322, accountType:CREDITS, balance:10000], " +
                "AccountDTO[nrb:26254111111111110000440001, accountType:SAVINGS, balance:23987.23]]"
    }

}

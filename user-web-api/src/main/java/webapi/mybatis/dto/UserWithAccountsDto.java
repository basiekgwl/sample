package webapi.mybatis.dto;

        import lombok.*;

        import java.util.List;
        import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWithAccountsDto {

    UserDto userDto;
    List<AccountDto> accountDtoList;

    @Override
    public String toString() {
        String listOfAccounts = accountDtoList.stream().map(AccountDto::toString).collect(Collectors.toList()).toString();
        return userDto.toString() + " " + listOfAccounts;
    }
}

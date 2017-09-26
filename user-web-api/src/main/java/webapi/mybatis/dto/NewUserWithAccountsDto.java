package webapi.mybatis.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserWithAccountsDto {

    private NewUserDto userDto;
    List<NewAccountDto> accountDtoList;

}

package mybatis.dto;


import lombok.*;
import mybatis.dao.UserEntity;
import mybatis.dto.interfaces.IUserDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto implements IUserDto {

    private String nik;
    private String fullName;
    private String nip;
    private String pesel;
    private String address;


    public UserDto returnUserData(UserEntity userEntity) {

        return UserDto.builder()
                .fullName(userEntity.getUserFullName())
                .nip(userEntity.getUserNip())
                .pesel(userEntity.getUserPesel())
                .address(userEntity.getUserAddress())
                .nik(userEntity.getNik())
                .build();
    }

}

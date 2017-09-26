package mybatis.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String nik;
    private String fullName;
    private String nip;
    private String pesel;
    private String address;


    @Override
    public String toString() {
        return String.format(
                "UserDTO[nik=%s, fullName='%s', NIP='%s', Pesel= '%s', address='%s']",
                getNik(), getFullName(), getNip(), getPesel(), getAddress());
    }
}

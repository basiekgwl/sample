package webapi.mybatis.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserDto {

    private String nik;
    private String fullName;
    private String nip;
    private String pesel;
    private String address;

}

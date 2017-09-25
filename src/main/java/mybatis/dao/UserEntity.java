package mybatis.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserEntity {

    private Long userId;

    private String userFullName;

    private String userNip;

    private String userPesel;

    private String userAddress;

    private String city;

    private String nik;

    private List<UserAccountEntity> userAccounts;

    public UserEntity() {
    }

    public UserEntity(String userFullName, String userNip, String userPesel, String userAddress, String city) {
        this.userFullName = userFullName;
        this.userNip = userNip;
        this.userPesel = userPesel;
        this.userAddress = userAddress;
        this.city = city;
    }

    @Builder
    public UserEntity(Long userId, String userFullName, String userNip, String userPesel, String userAddress, String city,String nik) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.userNip = userNip;
        this.userPesel = userPesel;
        this.userAddress = userAddress;
        this.city = city;
        this.nik = nik;
    }

    @Builder
    public UserEntity(String userFullName, String userNip, String userPesel, String userAddress, String city,String nik) {
        this.userFullName = userFullName;
        this.userNip = userNip;
        this.userPesel = userPesel;
        this.userAddress = userAddress;
        this.city = city;
        this.nik = nik;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, fullName='%s', NIP='%s', Pesel= '%s', address='%s', city='%s']",
                getUserId(), getUserFullName(), getUserNip(), getUserPesel(), getUserAddress(), getCity());
    }

}

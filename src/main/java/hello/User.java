package hello;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class User {

    private Long userId;

    private String userFullName;

    private String userNip;

    private String userPesel;

    private String userAddress;

    private String city;

    private List<UserAccounts> userAccounts;

    public User() {
    }

    public User(String userFullName, String userNip, String userPesel, String userAddress, String city) {
        this.userFullName = userFullName;
        this.userNip = userNip;
        this.userPesel = userPesel;
        this.userAddress = userAddress;
        this.city = city;
    }

    public User(Long userId, String userFullName, String userNip, String userPesel, String userAddress, String city) {
        this.userId = userId;
        this.userFullName = userFullName;
        this.userNip = userNip;
        this.userPesel = userPesel;
        this.userAddress = userAddress;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, fullName='%s', NIP='%s', Pesel= '%s', address='%s', city='%s']",
                getUserId(), getUserFullName(), getUserNip(), getUserPesel(), getUserAddress(), getCity());
    }

}

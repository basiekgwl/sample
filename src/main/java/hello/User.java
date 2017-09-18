package hello;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long userId;

    private String userFullName;

    private String userNip;

    private String userPesel;

    private String userAddress;

    private String city;

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

    public User(String userFullName, String userPesel, String userAddress) {
        this.userFullName = userFullName;
        this.userPesel = userPesel;
        this.userAddress = userAddress;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserNip() {
        return userNip;
    }

    public void setUserNip(String userNip) {
        this.userNip = userNip;
    }

    public String getUserPesel() {
        return userPesel;
    }

    public void setUserPesel(String userPesel) {
        this.userPesel = userPesel;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, fullName='%s', NIP='%s', Pesel= '%s', address='%s', city='%s']",
                getUserId(), getUserFullName(), getUserNip(), getUserPesel(), getUserAddress(), getCity());
    }

}

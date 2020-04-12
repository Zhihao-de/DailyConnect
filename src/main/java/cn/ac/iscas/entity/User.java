package cn.ac.iscas.entity;

import cn.ac.iscas.validation.RegexPatterns;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class User {
    private Integer id;

    private String userName;
    @Pattern(regexp = RegexPatterns.PhoneNumberPattern,
            message = "invalid_phone_number")
    private String telephone;

    private Date birthday;

    private Integer gender;

    private Integer role;

    private String password;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserKey(getId());
        userInfo.setUsername(getUserName());
        userInfo.setGender(getGender());
        userInfo.setTelephone(getTelephone());
        userInfo.setBirthday(getBirthday());
        userInfo.setUserRoles(getRole());

        return userInfo;
    }

}
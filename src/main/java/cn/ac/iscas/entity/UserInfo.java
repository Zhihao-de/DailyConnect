package cn.ac.iscas.entity;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {


    private Integer userKey;
    private Integer gender;
    private Date birthday;
    private Integer userRoles;
    private String telephone;
    private String username;


    public void setUserKey(Integer userKey) {
        this.userKey = userKey;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTelephone(String phoneNumber) {
        this.telephone = phoneNumber;
    }

    public void setUserRoles(Integer userRoles) {
        this.userRoles = userRoles;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

}

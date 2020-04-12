package cn.ac.iscas.dao;

import cn.ac.iscas.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    //add a new user
    int addUser(User record);

    //query use info
    User selectUserById(Integer userId);

    //query user by phoneNumber and password
    User selectUserByPass(User record);

    //query user by phoneNumber
    User selectUserByPhoneNumber(User Record);

    //update a user
    int updateUserById(User record);

    //delete a user
    int deleteUserById(Integer userId);


}
package cn.ac.iscas.controller;

import cn.ac.iscas.entity.User;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.user.UserRetrievalService;
import cn.ac.iscas.service.user.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "/users")
@Validated
public class UserController extends ControllerBase {

    @Autowired
    private UserRetrievalService urs;

    /**
     * 4
     * 个人主页 家长查看所有自己孩子所有的信息
     * para id
     * return event info
     */
    @RequestMapping(value = "/{uid}", method = RequestMethod.GET)
    //get user info
    public ResponseResult get(
            @PathVariable("uid") @Min(value = 1, message = "invalid_user_id") int userId
    ) {
        return urs.getAll(userId);
    }


    @Autowired
    private UserUpdateService uus;

    @RequestMapping(value = "/update/{uid}", method = RequestMethod.PATCH)
    //update user info
    public ResponseResult update(
            @PathVariable("uid") @Min(value = 1, message = "invalid_user_id") int userId,
            @RequestBody User user,
            BindingResult bindingResult

    ) {
        assert null != bindingResult;
        assert user.getId().equals(userId);
        return uus.update(user);
    }


    @RequestMapping(value = "/reset/{uid}", method = RequestMethod.PATCH)
    //update user password
    public ResponseResult reset(
            @PathVariable("uid") @Min(value = 1, message = "invalid_user_id") int userId,
            @RequestBody User user,
            BindingResult bindingResult

    ) {
        assert null != bindingResult;
        assert user.getId().equals(userId);
        return uus.update(user);
    }


}

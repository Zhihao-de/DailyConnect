package cn.ac.iscas.controller;

import cn.ac.iscas.entity.User;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.auth.BasicSignInService;
import cn.ac.iscas.service.auth.BasicSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/auth")
@Validated
public class authController extends ControllerBase {

    @Autowired
    private BasicSignUpService bsus;

    /**
     * 1.1
     * 注册
     * para 用户名 密码
     * return info
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    //basic sign up
    public ResponseResult signUp(
            @RequestParam String phoneNumber,
            @RequestParam String password
    ) {
        User user = new User();
        user.setTelephone(phoneNumber);
        user.setPassword(password);
        return bsus.signUp(user);
    }

    @Autowired
    private BasicSignInService bsis;

    /**
     * 1
     * 登录
     * para 用户名 密码
     * return token
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.GET)
    //basic sign in
    public ResponseResult SignIn(
            @RequestParam String phoneNumber,
            @RequestParam String password
    ) {
        return bsis.signIn(phoneNumber, password);
    }


}



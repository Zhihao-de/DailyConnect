package cn.ac.iscas.service.auth;

import cn.ac.iscas.entity.User;
import cn.ac.iscas.service.ResponseResult;

public interface SignUpService {

    /**
     * @param user
     */
    ResponseResult signUp(User user);
}

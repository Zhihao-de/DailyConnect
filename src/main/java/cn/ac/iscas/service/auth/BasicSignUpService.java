package cn.ac.iscas.service.auth;


import cn.ac.iscas.entity.User;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.security.MD5Utils;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class BasicSignUpService extends SignUpServiceBase implements SignUpService {

    @Override
    public ResponseResult signUp(@NotNull User user) {
        assert null != user.getTelephone();
        assert null != user.getPassword();

        try {
            //密码加密存储
            user.setPassword(MD5Utils.md5(user.getPassword()));
            userMapper.addUser(user);
            return ResponseResult.ok();
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SIGN_UP_ERROR, ex.getMessage());
        }

    }
}

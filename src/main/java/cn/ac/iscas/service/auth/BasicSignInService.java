package cn.ac.iscas.service.auth;

import cn.ac.iscas.dao.UserMapper;
import cn.ac.iscas.entity.User;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.security.MD5Utils;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class BasicSignInService {
    @Autowired
    private UserMapper uMapper;

    /**
     * 1 login
     * 用户登录，从输入的字符获取用户的用户名与密码，对密码进行md5的加密与数据库进行对比
     * 如果和数据库中存储的数据一致，则返回一个flag确认用户可以登录并进入下一个页面
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */

    public ResponseResult signIn(String phoneNumber, String password) {
        assert null != phoneNumber;
        assert null != password;

        try {
            String ciphertext = MD5Utils.md5(password);
            User user = new User();
            user.setTelephone(phoneNumber);
            user.setPassword(ciphertext);
            User res = uMapper.selectUserByPass(user);
            if (res == null) {
                return ResponseResult.error(ErrCodes.SIGN_IN_ERROR, "mismatched phone number and password.@");
            }

            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            //return the token, id of user and role of user
            return ResponseResult.ok().put("token", MD5Utils.md5(phoneNumber + dateFormat.format(calendar.getTime())))
                    .put("id", res.getId())
                    .put("role", res.getRole());

        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SIGN_UP_ERROR, ex.getMessage());
        }

    }


}

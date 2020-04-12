package cn.ac.iscas.service.user;


import cn.ac.iscas.dao.UserMapper;
import cn.ac.iscas.entity.User;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.security.MD5Utils;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@Service
public class UserUpdateService {

    @Autowired
    private UserMapper userMapper;

    /**
     * @param user update user by id and password
     */
    @Transactional
    public ResponseResult update(@NotNull User user) {
        assert null != user.getId();
        try {
            if (null != user.getPassword()) {
                user.setPassword(user.getPassword());
            }
            userMapper.updateUserById(user);
            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }

    }

    /**
     * @param user update user by id and password
     */
    @Transactional
    public ResponseResult reset(@NotNull User user) {
        assert null != user.getId();
        try {
            if (null != user.getPassword()) {
                user.setPassword(MD5Utils.md5(user.getPassword()));
            }
            userMapper.updateUserById(user);
            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }

    }

}

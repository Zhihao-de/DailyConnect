package cn.ac.iscas.service.auth;

import cn.ac.iscas.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class SignUpServiceBase {

    @Autowired
    protected UserMapper userMapper;
}

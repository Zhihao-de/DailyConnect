package cn.ac.iscas.service.user;

import cn.ac.iscas.entity.User;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserUpdateServiceTest extends TestBase {

    @Autowired
    private UserUpdateService uus;

    @Before
    public void setUp() throws Exception {
        super.createContext();
        uus = applicationContext.getBean(UserUpdateService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(2);
        user.setTelephone("18310880994");
        ResponseResult rr = uus.update(user);
        System.out.println(rr);
    }

}

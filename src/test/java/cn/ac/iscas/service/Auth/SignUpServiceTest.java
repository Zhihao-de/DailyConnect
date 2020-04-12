package cn.ac.iscas.service.Auth;

import cn.ac.iscas.entity.User;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import cn.ac.iscas.service.auth.BasicSignUpService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SignUpServiceTest extends TestBase {

    @Autowired
    private BasicSignUpService bsus;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        bsus = applicationContext.getBean(BasicSignUpService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGuardianshipCreateService() {
        User user = new User();
        user.setTelephone("19090909089");
        user.setPassword("1234567");
        ResponseResult rr = bsus.signUp(user);
        System.out.println(rr.getCode());
    }
}

package cn.ac.iscas.service.Auth;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import cn.ac.iscas.service.auth.BasicSignInService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SignInServiceTest extends TestBase {

    @Autowired
    private BasicSignInService bsis;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        bsis = applicationContext.getBean(BasicSignInService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSignInService() {
        ResponseResult rr = bsis.signIn("19090909089", "1234567");
        System.out.println(rr.getCode());
        System.out.println(rr.getData());

    }

}

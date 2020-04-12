package cn.ac.iscas.service.relation;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GuardianshipTest extends TestBase {

    @Autowired
    private GuardianShipCreateService gscs;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        gscs = applicationContext.getBean(GuardianShipCreateService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGuardianshipCreateService() {
        ResponseResult rr = gscs.create(333, "lili");
        System.out.println(rr.getCode());
    }


}

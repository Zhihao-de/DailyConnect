package cn.ac.iscas.service.user;

import cn.ac.iscas.entity.User;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRetrievalServiceTest extends TestBase {
    @Autowired
    private UserRetrievalService urs;

    @Before
    public void setUp() throws Exception {
        super.createContext();
        urs = applicationContext.getBean(UserRetrievalService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrievalUserPass1() throws Exception {

        ResponseResult rr = urs.get(1);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());

        Assert.assertEquals(rr.getCode(), 200);
    }

    @Test
    public void testRetrievalUserPass2() throws Exception {
        User user = new User();
        ResponseResult rr = urs.get(11231);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());

        Assert.assertEquals(rr.getCode(), -4002);
    }

    @Test
    public void testGetAll() throws Exception {
        ResponseResult rr = urs.getAll(222);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());

    }


}

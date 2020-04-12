package cn.ac.iscas.service.Tag;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import cn.ac.iscas.service.tag.TagCreateService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestTagCreateService extends TestBase {
    @Autowired
    private TagCreateService tcs;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        tcs = applicationContext.getBean(TagCreateService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTagCreateService() {
        int[] parent = {1001, 1002, 1004};
        ResponseResult rr = tcs.CreateGroup(1, "fsdjfn", parent);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());
    }


}

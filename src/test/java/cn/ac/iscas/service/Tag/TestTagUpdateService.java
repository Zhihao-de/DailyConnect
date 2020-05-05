package cn.ac.iscas.service.Tag;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import cn.ac.iscas.service.tag.TagUpdateService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestTagUpdateService extends TestBase {

    @Autowired
    private TagUpdateService tus;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        tus = applicationContext.getBean(TagUpdateService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTagUpdateService() {
        int[] students = {333, 1018};
        ResponseResult rr = tus.updateMember(29, 1, students);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());
    }

}

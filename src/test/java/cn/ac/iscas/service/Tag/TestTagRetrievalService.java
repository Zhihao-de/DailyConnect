package cn.ac.iscas.service.Tag;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import cn.ac.iscas.service.tag.TagRetrievalService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestTagRetrievalService extends TestBase {

    @Autowired
    private TagRetrievalService trs;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        trs = applicationContext.getBean(TagRetrievalService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTagReterievalService1() {
        ResponseResult rr = trs.getTagByTeacher(1);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());
    }

    @Test
    public void testTagReterievalService2() {
        ResponseResult rr = trs.getTagById(2);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());
    }

    @Test
    public void testTagReterievalService3() {
        ResponseResult rr = trs.getTagInfo(29);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());
    }
}

package cn.ac.iscas.service.comments;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentRetrievalServiceTest extends TestBase {

    @Autowired
    private CommentsRetrievalService crs;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        crs = applicationContext.getBean(CommentsRetrievalService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCommentRetrievalService() {
        ResponseResult rr = crs.queryCommentsByTitleId(1);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());
    }

}

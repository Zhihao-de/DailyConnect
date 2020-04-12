package cn.ac.iscas.service.comments;

import cn.ac.iscas.entity.Comments;
import cn.ac.iscas.service.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommentCreateServiceTest extends TestBase {

    @Autowired
    private CommentCreateService ccs;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        ccs = applicationContext.getBean(CommentCreateService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCommentCreateService() {
        Comments cm = new Comments();
        cm.setTitleId(1);
        cm.setUid(888);
        cm.setTid(1);
        cm.setContent("这次作业是坨屎");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cm.setCommentTime(c.getTime());
        ccs.create(cm);
    }

}

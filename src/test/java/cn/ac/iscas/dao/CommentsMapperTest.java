package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Comments;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CommentsMapperTest {


    private ApplicationContext applicationContext;

    @Autowired
    private CommentsMapper cMapper;


    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        // 导入需要测试的
        cMapper = applicationContext.getBean(CommentsMapper.class);
    }

    @Test
    public void addComment() {
        Comments cm = new Comments();
        cm.setTitleId(1);
        cm.setUid(888);
        cm.setTid(1);
        cm.setContent("这次作业拉稀");

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cm.setCommentTime(c.getTime());
        cMapper.addComment(cm);

    }

    @Test
    public void selectCommentById() {
        Comments com = cMapper.selectCommentById(2);
        System.out.println(com.getId());
        System.out.println(com.getTitleId());
        System.out.println(com.getUid());
        System.out.println(com.getContent());
        System.out.println(com.getCommentTime());
    }

    @Test
    public void selectCommentsByTitleId() {
        Comments[] coms = cMapper.selectCommentsByTitleId(1);
        for (Comments com : coms) {
            System.out.println(com);
        }
    }

    @Test
    public void deleteComment() {
        cMapper.deleteCommentById(1);
    }

}
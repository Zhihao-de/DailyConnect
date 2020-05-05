package cn.ac.iscas.service.assignments;

import cn.ac.iscas.entity.Assignments;
import cn.ac.iscas.service.Assignments.AssignmentUpdateService;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class AssignmentUpdateServiceTest extends TestBase {


    @Autowired
    private AssignmentUpdateService ars;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        ars = applicationContext.getBean(AssignmentUpdateService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    //更新作业的url或者内容
    public void testGetAssignment() {

        Assignments newAssignment = new Assignments();
        newAssignment.setId(1);
        newAssignment.setContent("我家孩子这次作业完成的不错，请老师查看图片与视频！");
        newAssignment.setUrls("www.baidu.com");
        ResponseResult rr = ars.update(newAssignment);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());

    }

}

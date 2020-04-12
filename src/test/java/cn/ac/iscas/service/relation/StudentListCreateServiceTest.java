package cn.ac.iscas.service.relation;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentListCreateServiceTest extends TestBase {

    @Autowired
    private TeacherStudentRelationCreateService tsrcs;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        tsrcs = applicationContext.getBean(TeacherStudentRelationCreateService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCreateTSR() {

        ResponseResult rr = tsrcs.create(1, 222);
        System.out.println(rr.getCode());


    }
}

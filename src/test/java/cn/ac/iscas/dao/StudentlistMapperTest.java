package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Studentlist;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentlistMapperTest {

    private ApplicationContext applicationContext;

    @Autowired
    private StudentlistMapper smapper;


    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        // 导入需要测试的
        smapper = applicationContext.getBean(StudentlistMapper.class);

    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addTeaStuShip() {
        Studentlist sl = new Studentlist();
        sl.setTeacherId(1);
        sl.setParentId(222);
        int res = smapper.addTeaStuShip(sl);

    }

    @Test
    public void selectStudentByTeacherId() {

        Studentlist[] res = smapper.selectStudentsByTeacherId(1);
        for (Studentlist re : res) {
            System.out.println(re.getId());
        }
        Assert.assertTrue(res[0].getParentId() == 888);

    }

    @Test
    public void selectTeacherByParentId() {
        Studentlist res = smapper.selectTeacherByParentId(222);
        Assert.assertTrue(res.getTeacherId() == 1);
    }

    @Test
    public void selectStudentsByTag() {
        Studentlist[] res = smapper.selectStudentsByTag("new");
        for (Studentlist x : res) {
            System.out.println(x.getStudentName());
        }
    }
}
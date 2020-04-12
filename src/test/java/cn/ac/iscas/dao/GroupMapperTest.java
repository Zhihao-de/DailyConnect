package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Group;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GroupMapperTest {
    private ApplicationContext applicationContext;

    @Autowired
    private GroupMapper mapper;


    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        // 导入需要测试的
        mapper = applicationContext.getBean(GroupMapper.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGroup() {
        Group g = new Group();
        g.setTagId(1);
        g.setParentId(222);
        g.setTeacher("Lisap");
        g.setStudent("Jack");
        g.setTeacherId(1);
        mapper.addGroup(g);
    }

    @Test
    public void queryGroup() {
        Group[] res = mapper.selectByTagId(1);
        for (Group x : res) {
            System.out.println(x.getTagId());
        }
    }

    @Test
    public void deleteGroup() {
        mapper.deleteByTagId(1);
    }


}

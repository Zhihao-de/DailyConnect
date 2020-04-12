package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Tag;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TagMapperTest {

    private ApplicationContext applicationContext;

    @Autowired
    private TagMapper mapper;


    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        // 导入需要测试的
        mapper = applicationContext.getBean(TagMapper.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addTag() {
        Tag tag = new Tag();
        tag.setTeacher(2);
        tag.setTag("touteng");
        mapper.addTag(tag);
    }

    @Test
    public void getTagById() {
        Tag tag = mapper.selectTagById(1);
        System.out.println(tag.getTag());
        System.out.println(tag.getTeacher());

    }

    @Test
    public void deleteById() {
        mapper.deleteTagById(1);
    }
}

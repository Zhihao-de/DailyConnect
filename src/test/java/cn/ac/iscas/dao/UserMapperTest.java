package cn.ac.iscas.dao;

import cn.ac.iscas.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMapperTest {

    private ApplicationContext applicationContext;

    @Autowired
    private UserMapper mapper;

    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        // 导入需要测试的
        mapper = applicationContext.getBean(UserMapper.class);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void addUser() {
        System.out.println("test add query");
        User user = new User();
        user.setUserName("张三");
        user.setTelephone("18310880998");
        user.setPassword("password");
        user.setRole(1);
        user.setGender(1);
        System.out.println(user.getUserName());
        int result = mapper.addUser(user);
        System.out.println(result);
        assert (result == 1);
    }

    @Test
    public void selectUserById() {
        System.out.println("test select query:");
        User res = mapper.selectUserById(1);
        System.out.println(res.getUserName());
        Assert.assertTrue(res.getId() == 1);

    }

    @Test
    public void updateUserById() {
        System.out.println("test update query");
        User user = new User();
        user.setId(1);
        user.setUserName("Lisap");
        user.setTelephone("18310880998");
        user.setPassword("password");
        user.setRole(1);
        user.setGender(1);

        int res = mapper.updateUserById(user);
        User check = mapper.selectUserById(1);
        System.out.println(check.getUserName());
        Assert.assertTrue(check.getUserName().equals("Lisap"));
    }

    @Test
    public void deleteUserById() {
        System.out.println("test delete query");
        User user = new User();
        user.setId(1000);
        user.setUserName("Peter");
        user.setRole(1);
        user.setGender(1);
        user.setTelephone("12929293736");
        user.setPassword("password");

        int res = mapper.addUser(user);

        int delRes = mapper.deleteUserById(1000);

        Assert.assertTrue(delRes == 1);

    }


    @Test
    //test user sign up
    public void testSignUp() {
        User user = new User();
        user.setTelephone("18310881111");
        user.setPassword("password");
        int res = mapper.addUser(user);


    }
}
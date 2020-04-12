package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Guardianship;
import cn.ac.iscas.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GuardianshipMapperTest {

    private ApplicationContext applicationContext;

    @Autowired
    private GuardianshipMapper pmapper;
    private UserMapper umapper;

    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        // 导入需要测试的
        pmapper = applicationContext.getBean(GuardianshipMapper.class);
        umapper = applicationContext.getBean(UserMapper.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGuardianship() {
        //add guardianship info
        /*User mother = new User();
        mother.setUserid(333);
        mother.setGender(1);
        mother.setPhonenumber("13838383838");
        mother.setUsername("PAP");
        mother.setPassword("dasdasdada");
        mother.setRole(0);
        int res1 = umapper.addUser(mother);
*/
        Guardianship guardian = new Guardianship();
        guardian.setParentId(222);
        guardian.setStudent("Jackieyyy");
        //get parent name by parentid
        User mom = umapper.selectUserById(999);
        guardian.setParent(mom.getUserName());
        int res = pmapper.addGuardianship(guardian);

        Assert.assertTrue(res == 1);
    }


}
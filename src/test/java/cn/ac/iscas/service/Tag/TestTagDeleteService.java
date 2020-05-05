package cn.ac.iscas.service.Tag;

import cn.ac.iscas.service.TestBase;
import cn.ac.iscas.service.tag.TagDeleteService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestTagDeleteService extends TestBase {
    @Autowired
    private TagDeleteService tds;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        tds = applicationContext.getBean(TagDeleteService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testTagDeleteService() {

        tds.deleteTag(12);
    }

}

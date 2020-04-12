package cn.ac.iscas.service.event;


import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import cn.ac.iscas.service.events.EventRetrievalService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class EventRetrievalServiceTest extends TestBase {
    @Autowired
    private EventRetrievalService ers;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        ers = applicationContext.getBean(EventRetrievalService.class);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testGetEvent() {

        ResponseResult rr = ers.get(1);
        System.out.println(rr.getCode());
        System.out.println(rr.getMsg());
        System.out.println(rr.getData());

    }
}

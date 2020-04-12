package cn.ac.iscas.service.event;

import cn.ac.iscas.entity.Events;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import cn.ac.iscas.service.events.EventCreateService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventCreateServiceTest extends TestBase {

    @Autowired
    private EventCreateService ecs;


    @Before
    public void setUp() throws Exception {
        super.createContext();
        ecs = applicationContext.getBean(EventCreateService.class);
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testEventCreateService() {

        Events ev = new Events();
        ev.setEventName("dasdas");
        ev.setTeacher(1);
        ev.setCategories(1);
        ev.setEventContent("cascascacacas");

        ev.setFrequency(1);


        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(c.getTime()));
        ev.setCreateTime(c.getTime());
        ResponseResult rr = ecs.create(ev);
        System.out.println(rr.getCode());
    }


}

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
        ev.setEventName("课程5");
        ev.setTeacher(1);
        ev.setCategories(1);
        ev.setEventContent("听说训练");
        ev.setFrequency(1);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(c.getTime()));
        ev.setCreateTime(c.getTime());
        int[] shareTags = {};
        int[] shareObjects = {};
        ResponseResult rr = ecs.create(ev, 0, shareTags, shareObjects);
        System.out.println(rr.getCode());

    }

    @Test
    public void testEventCreateService1() {

        Events ev = new Events();
        ev.setEventName("课程10");
        ev.setTeacher(1);
        ev.setCategories(1);
        ev.setEventContent("训练20");
        ev.setFrequency(1);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(c.getTime()));
        ev.setCreateTime(c.getTime());
        int[] shareTags = {2, 29};
        int[] shareObjects = {};
        ResponseResult rr = ecs.create(ev, 1, shareTags, shareObjects);
        System.out.println(rr.getCode());

    }


    @Test
    public void testEventCreateService2() {

        Events ev = new Events();
        ev.setEventName("课程11");
        ev.setTeacher(1);
        ev.setCategories(1);
        ev.setEventContent("玩训练");
        ev.setFrequency(1);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(c.getTime()));
        ev.setCreateTime(c.getTime());
        int[] shareTags = {};
        int[] shareObjects = {888, 999, 1018};
        ResponseResult rr = ecs.create(ev, 2, shareTags, shareObjects);
        System.out.println(rr.getCode());

    }

}

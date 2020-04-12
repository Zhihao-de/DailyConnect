package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Events;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventsMapperTest {


    private ApplicationContext applicationContext;

    @Autowired
    private EventsMapper mapper;


    @Before
    public void setUp() throws Exception {
        // 加载spring配置文件
        applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
        // 导入需要测试的
        mapper = applicationContext.getBean(EventsMapper.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addEvent() {
        Events ev = new Events();
        ev.setEventName("dasdas");
        ev.setTeacher(1);
        ev.setEventContent("cascascacacas");
        ev.setFrequency(1);
        ev.setCategories(1);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(c.getTime()));
        ev.setCreateTime(c.getTime());
        int res = mapper.addEvent(ev);
    }

    @Test
    public void getEventlist() {
        Events[] evs = mapper.selectEventsByTeacherId(1);
        for (Events ev : evs) {
            System.out.println(ev.getId());
        }
    }

    @Test
    public void getEventById() {
        Events ev = mapper.selectEventById(1);
        System.out.println(ev.getId());
        System.out.println(ev.getCategories());
        System.out.println(ev.getEventContent());
        System.out.println(ev.getEventName());
        System.out.println(ev.getCreateTime());
    }

    @Test
    public void selectEventByTeahcerId() {
        Events[] evs = mapper.selectEventsByTeacherId(1);
        for (Events ev : evs) {
            System.out.println(ev.getEventName());
        }
    }


    @Test
    public void updateEvent() {
        Events ev = new Events();
        ev.setId(5);
        ev.setEventName("new");
        ev.setTeacher(2);
        ev.setEventContent("nwq");

        int res = mapper.updateEventById(ev);
    }
}
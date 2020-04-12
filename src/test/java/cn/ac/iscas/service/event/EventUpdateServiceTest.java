package cn.ac.iscas.service.event;

import cn.ac.iscas.entity.Events;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.TestBase;
import cn.ac.iscas.service.events.EventUpdateService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EventUpdateServiceTest extends TestBase {
    @Autowired
    private EventUpdateService eus;

    @Before
    public void setUp() throws Exception {
        super.createContext();
        eus = applicationContext.getBean(EventUpdateService.class);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testEventUpdateService() {
        Events ev = new Events();
        ev.setId(5);
        ev.setEventName("99999999");
        ev.setTeacher(1);
        ev.setEventContent("c888888888888s");
        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(dateFormat.format(c.getTime()));
        ev.setCreateTime(c.getTime());
        ResponseResult rr = eus.update(ev);
        System.out.println(rr.getCode());
        System.out.println(ev.getId());
    }

}

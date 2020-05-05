package cn.ac.iscas.controller;


import cn.ac.iscas.entity.Events;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.events.EventCreateService;
import cn.ac.iscas.service.events.EventRetrievalService;
import cn.ac.iscas.service.events.EventUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "/event")
@Validated
public class EventController extends ControllerBase {

    @Autowired
    private EventCreateService ecs;

    /**
     * 10
     * 创建事件页面
     * para event
     * return ok
     */
    // create an event
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseResult create(
            @RequestBody Events event,
            @RequestParam int shareType,
            @RequestParam(value = "tags") int[] tags,
            @RequestParam(value = "objects") int[] objects
    ) {
        return ecs.create(event, shareType, tags, objects);
    }


    @Autowired
    private EventUpdateService eus;

    /**
     * 8 作业详情页 更新
     * 登录
     * para event
     * return token
     */
    //update an event
    @RequestMapping(value = "/update/{eid}", method = RequestMethod.PATCH)
    public ResponseResult update(
            @PathVariable("eid") @Min(value = 1, message = "invalid_event_id") int eventId,
            @RequestBody Events event,
            BindingResult bindingResult
    ) {
        assert null != bindingResult;
        assert event.getId().equals(eventId);
        return eus.update(event);
    }

    @Autowired
    private EventRetrievalService ers;

    /**
     * 6
     * 查看作业详情
     * para eventID
     * return event info
     */
    //query an event by eventId
    @RequestMapping(value = "/{eid}", method = RequestMethod.GET)
    public ResponseResult query(
            @PathVariable("eid") @Min(value = 1, message = "invalid_event_id") int eventId
    ) {
        return ers.get(eventId);
    }

    /**
     * 3
     * 老师查看自己创建的所有事件
     * para id 老师的ID
     * return 所有老师创建的事件
     */

    //query an event by teacherId 老师查询自己创建的事件ID
    @RequestMapping(value = "/teacher/{tid}", method = RequestMethod.GET)
    public ResponseResult getByTeacherId(
            @PathVariable("tid") @Min(value = 1, message = "invalid_event_id") int teacherId
    ) {
        return ers.getByTeacherId(teacherId);
    }


}

package cn.ac.iscas.service.events;

import cn.ac.iscas.dao.EventsMapper;
import cn.ac.iscas.entity.Events;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class EventRetrievalService {

    @Autowired
    private EventsMapper eventsMapper;

    /**
     * 3主页面作业列表
     * 根据老师的Id查询其穿件的事件内容
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult getByTeacherId(int teacherId) {
        assert 0 < teacherId;
        try {
            Events[] events = eventsMapper.selectEventsByTeacherId(teacherId);

            if (null == events) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing event from the teacher");
            }
            return ResponseResult.ok().put("events", events);
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

    /**
     * 5. 根据event 的开始结束时间 和老师的ID 来查找事件
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult getByDate(Date date, int id) {
        assert date != null;
        try {
            Events[] events = eventsMapper.selectEventsByTeacherId(id);
            if (null == events) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing event from the teacher");
            }
            List<Events> ev = new ArrayList();
            for (Events event : events) {
                if (date.getTime() >= event.getStartTime().getTime() && date.getTime() <= event.getEndTime().getTime()) {
                    ev.add(event);
                }
            }
            return ResponseResult.ok().put("events", ev.toArray(new Events[ev.size()]));

        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }

    }

    /**
     * 6. 根据事件的Id查询其穿件的事件内容
     * 11. 事件的详细页
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult get(int eventId) {
        assert 0 < eventId;
        try {
            Events event = eventsMapper.selectEventById(eventId);

            if (null == event) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing event from the teacher");
            }
            return ResponseResult.ok().put("event", event);
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

}

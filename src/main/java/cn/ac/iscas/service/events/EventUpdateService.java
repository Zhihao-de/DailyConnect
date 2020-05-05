package cn.ac.iscas.service.events;

import cn.ac.iscas.dao.EventsMapper;
import cn.ac.iscas.entity.Events;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EventUpdateService {

    @Autowired
    private EventsMapper eMapper;


    /**
     * 8 某作业的详细页_上传图片或视频(只有家长可操作)
     * 更新事件内容
     *
     * @param event
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */

    public ResponseResult update(Events event) {
        try {
            assert 0 < event.getId();

            Events newEvent = new Events();
            newEvent.setId(event.getId());
            if (event.getCategories() != null) {
                newEvent.setCategories(event.getCategories());
            }
            if (event.getEventName() != null) {
                newEvent.setEventName(event.getEventName());
            }
            if (event.getEventContent() != null) {
                newEvent.setEventContent(event.getEventContent());
            }
            if (event.getCreateTime() != null) {
                newEvent.setCreateTime(event.getCreateTime());
            }
            if (event.getStartTime() != null) {
                newEvent.setStartTime(event.getStartTime());
            }
            if (event.getEndTime() != null) {
                newEvent.setEndTime(event.getEndTime());
            }

            eMapper.updateEventById(newEvent);

            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }


    }


}

package cn.ac.iscas.service.events;


import cn.ac.iscas.dao.EventsMapper;
import cn.ac.iscas.entity.Events;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventCreateService {
    @Autowired
    private EventsMapper eMapper;

    /**
     * 10 事件穿件页 只有老师可以见
     * 创建一个事件
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult create(Events event) {
        try {

            assert null != event.getCategories();
            assert null != event.getEventName();
            assert null != event.getEventContent();
            assert null != event.getCreateTime();
            assert null != event.getShareType();
            assert null != event.getFrequency();
            eMapper.addEvent(event);

            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }


    }

}

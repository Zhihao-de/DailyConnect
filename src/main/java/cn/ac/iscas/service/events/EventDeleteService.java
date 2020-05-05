package cn.ac.iscas.service.events;

import cn.ac.iscas.dao.EventsMapper;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventDeleteService {

    @Autowired
    private EventsMapper eMapper;

    /**
     * 删除一个事件
     *
     * @param
     */
    public ResponseResult delete(int eventId) {
        try {
            assert 0 < eventId;
            eMapper.deleteEventById(eventId);
            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }

    }
}

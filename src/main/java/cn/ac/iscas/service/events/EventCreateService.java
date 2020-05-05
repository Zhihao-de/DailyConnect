package cn.ac.iscas.service.events;


import cn.ac.iscas.dao.EventsMapper;
import cn.ac.iscas.dao.ForwardMapper;
import cn.ac.iscas.dao.GroupMapper;
import cn.ac.iscas.dao.StudentsMapper;
import cn.ac.iscas.entity.Events;
import cn.ac.iscas.entity.Forward;
import cn.ac.iscas.entity.Group;
import cn.ac.iscas.entity.Students;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventCreateService {
    @Autowired
    private EventsMapper eMapper;

    @Autowired
    private ForwardMapper fMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private StudentsMapper studentsMapper;


    /**
     * 10 事件穿件页 只有老师可以见
     * 创建一个事件
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult create(Events event, int shareType, int[] shareTags, int[] shareObjects) {
        try {
            //创建事件
            assert null != event.getCategories();
            assert null != event.getEventName();
            assert null != event.getEventContent();
            assert null != event.getCreateTime();
            assert null != event.getFrequency();
            assert null != event.getTeacher();
            eMapper.addEvent(event);

            System.out.println(event.getEventName());


            //查询事件的ID
            Events res = eMapper.selectEventByName(event.getEventName());

            //转发事件
            //分三种类型
            //shareType:
            //0:给该老师的所有学生转发
            //1:给该老师下某些tag的学生转发
            //2:给该老师指定的某些学生转发


            if (shareType == 0) {
                //查询老师的所有学生的列表
                //遍历当前老师的所有学生，并转发
                Students[] studentlist = studentsMapper.selectStudentsByTeacherId(event.getTeacher());
                for (Students stu : studentlist) {
                    Forward forward = new Forward();
                    forward.setEventId(res.getId());
                    forward.setTeacherId(event.getTeacher());
                    forward.setStuId(stu.getStudentId());
                    fMapper.addForwardRecord(forward);
                }
            } else if (shareType == 1) {
                //遍历当前老师当前的Tags信息得到学生的ID，并转发
                List<Integer> list = new ArrayList<>();
                if (shareTags == null || shareTags.length == 0) {
                    ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, "missing tags info!");
                }
                //遍历tag的学生ID
                for (int tagId : shareTags) {
                    Group[] group_info = groupMapper.selectByTagId(tagId);
                    //
                    for (Group group : group_info) {
                        System.out.println(group.getStudent());
                        if (!list.contains(group.getStudentId())) {
                            list.add(Integer.valueOf(group.getStudentId()));
                        }
                    }
                }
                System.out.println("这里是学生列表");
                for (Integer x : list) {
                    System.out.println(x);
                }
                //向转发表中添加记录
                for (Integer stu_id : list) {
                    Forward forward = new Forward();
                    forward.setEventId(res.getId());
                    forward.setTeacherId(event.getTeacher());
                    forward.setStuId(stu_id.intValue());
                    fMapper.addForwardRecord(forward);
                }

            } else if (shareType == 2) {
                //得到所有的学生ID进行转发

                if (shareObjects == null || shareObjects.length == 0) {
                    ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, "missing objects info!");
                }
                for (int obj : shareObjects) {
                    Forward forward = new Forward();
                    forward.setEventId(res.getId());
                    forward.setTeacherId(event.getTeacher());
                    forward.setStuId(obj);
                    fMapper.addForwardRecord(forward);
                }

            }

            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }


    }


}

package cn.ac.iscas.service.user;


import cn.ac.iscas.dao.*;
import cn.ac.iscas.entity.*;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserRetrievalService {

    @Autowired
    private UserMapper userMapper;
    /**
     * 获取用户信息.
     *
     * @param userId 用户id
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */

    public ResponseResult get(int userId) {
        assert 0 < userId;
        try {
            User user = userMapper.selectUserById(userId);
            if (null == user) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing user");
            }

            UserInfo ui = user.getUserInfo();

            return ResponseResult.ok().put("userInfo", ui);
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

    /**
     * 4
     * 获取用户所有的信息 包含个人信息与课程信息.
     *
     * @param userId    用户id
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */


    @Autowired
    private EventsMapper eMapper;
    @Autowired
    private ForwardMapper fMapper;
    @Autowired
    private StudentsMapper slMapper;
    @Autowired
    private GroupMapper groupMapper;

    public ResponseResult getAll(int userId) {
        assert 0 < userId;
        try {
            //查询用户自己的信息(姓名+ID)
            User user = userMapper.selectUserById(userId);

            if (null == user) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing user");
            } else {
                System.out.println("查到的用户的信息");
                System.out.println(user.getId());
                System.out.println(user.getUserName());
            }

            // 根据学生的Id查询老师的ID
            Students sl = slMapper.selectInfoByStudentId(userId);
            System.out.println(sl);
            if (null == sl) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing user");
            } else {
                System.out.println("查到了老师的信息");
                System.out.println(sl.getTeacherName());
                System.out.println(sl.getTeacherId());
            }

            //根据老师和学生的ID查TAG信息 这里需要修改
            Group[] groups = groupMapper.selectByStudent(userId);
            String[] tag_res = new String[groups.length];
            System.out.println(groups);
            if (null == groups) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing tags");
            } else {
                System.out.println("查到了标签的信息");
                for (int i = 0; i < groups.length; i++) {
                    System.out.println(groups[i].getTagId() + ":" + groups[i].getTag());
                    tag_res[i] = groups[i].getTag();
                }
            }


            //根据转发列表 查询课程的信息
            Forward[] forwards = fMapper.selectRecordsByStudentId(userId);

            //包含用户的课程信息
            List<Events> event_res = new ArrayList<Events>();
            if (null == forwards) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing records");
            } else {
                System.out.println("查到了课程的信息");
                for (int i = 0; i < forwards.length; i++) {
                    System.out.println(forwards[i].getEventId());
                    //查找课程的ID查询课程的信息
                    event_res.add(eMapper.selectEventById(forwards[i].getEventId()));
                }
            }

            return ResponseResult.ok()
                    .put("id", userId)
                    .put("name", user.getUserName())
                    .put("tags", tag_res)
                    .put("events", event_res);
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

}

package cn.ac.iscas.service.user;


import cn.ac.iscas.dao.EventsMapper;
import cn.ac.iscas.dao.StudentlistMapper;
import cn.ac.iscas.dao.TagMapper;
import cn.ac.iscas.dao.UserMapper;
import cn.ac.iscas.entity.*;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private StudentlistMapper slMapper;
    @Autowired
    private TagMapper tagMapper;

    public ResponseResult getAll(int userId) {
        assert 0 < userId;
        try {
            //查询用户自己的信息
            User user = userMapper.selectUserById(userId);

            if (null == user) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing user");
            } else {
                System.out.println("查到的用户的信息");
                System.out.println(user.getId());
            }
            // 根据家长的Id查询老师的ID与与学生的姓名与tags
            Studentlist sl = slMapper.selectByParentId(userId);
            System.out.println(sl);
            if (null == sl) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing user");
            } else {

                System.out.println("查到了老师的信息");
                System.out.println(sl.getTeacherName());
                System.out.println(sl.getTeacherId());
            }

            int teacherId = sl.getTeacherId();
            //学生姓名
            String stu_name = sl.getStudentName();
            String stu_tag = sl.getTag();

            //学生的tagsId
            String[] stu_tags = stu_tag.split("%");
            Tag[] tag_res = new Tag[stu_tags.length];

            for (int i = 0; i < stu_tags.length; i++) {
                System.out.println();
                tag_res[i] = tagMapper.selectTagById(Integer.valueOf(stu_tags[i]));
            }
            //查询该老师所有课程的信息
            Events[] evs = eMapper.selectEventsByTeacherId(teacherId);
            if (null == evs) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing user");
            } else {
                System.out.println("查到了课程的信息");
                for (int i = 0; i < evs.length; i++) {
                    System.out.println(evs[i].getEventName());
                }
            }

            //包含用户的课程信息
            List<Events> list = new ArrayList<Events>();
            for (Events ev : evs) {
                //share type 0所有学生 1某个tag 2单独的学生一群学生
                if (ev.getShareType() == 0) {
                    list.add(ev);
                } else
                    //如果分享方式为tags的话，看学生的tag是否和分享的tag有交集
                    if (ev.getShareType() == 1) {
                        //找出所有分享的tagId
                        String tags_str = ev.getShareTag();
                        String[] tags = tags_str.split("%");
                        //找出所有学生拥有的tagId
                        Set<String> set2 = new HashSet<>(Arrays.asList(tags));
                        Set<String> set1 = new HashSet<>(Arrays.asList(stu_tags));
                        //判断交集
                        if (CollectionUtils.intersection(set1, set2).size() != 0) {
                            list.add(ev);
                        }
                    } else
                        //如果分享的方式是个人的话， 看学生在不在分享的list中
                        if (ev.getShareType() == 2) {
                            //从单个的学生中找出该学生的课程信息
                            String individuals = ev.getShareIndividuals();
                            String[] inds = individuals.split("%");
                            Set<String> set = new HashSet<>(Arrays.asList(inds));
                            //存在字符串中的家长Id
                            if (set.contains(Integer.toString(userId))) {
                                list.add(ev);
                            }

                        }
            }
            //设置事件返回数组
            Events[] ev_res = new Events[list.size()];
            for (int i = 0; i < list.size(); i++) {
                ev_res[i] = list.get(i);
            }
            return ResponseResult.ok()
                    .put("id", userId)
                    .put("name", stu_name)
                    .put("tags", tag_res)
                    .put("events", list);
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

}

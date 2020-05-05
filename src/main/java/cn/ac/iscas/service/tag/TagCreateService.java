package cn.ac.iscas.service.tag;


import cn.ac.iscas.dao.GroupMapper;
import cn.ac.iscas.dao.TagMapper;
import cn.ac.iscas.dao.UserMapper;
import cn.ac.iscas.entity.Group;
import cn.ac.iscas.entity.Tag;
import cn.ac.iscas.entity.User;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TagCreateService {

    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 14 创建标签页
     * 老师创建tag(tag重新存储在tag的表中,并且将学生的信息tag的归属信息存在group表中)
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult CreateGroup(int teacherId, String tag, int[] user_ids) {
        try {
            //在tag表中添加一条记录
            Tag new_tag = new Tag();
            new_tag.setTeacher(teacherId);
            new_tag.setTag(tag);

            if (new_tag.getTag() != null && new_tag.getTeacher() != null) {
                tagMapper.addTag(new_tag);
                System.out.println();
            }

            //查询该tag 的id
            Tag term_tag = tagMapper.selectTagByName(tag);
            System.out.println("加入了新的tag");
            System.out.println(term_tag.getId());
            //查询老师的姓名
            User teacher = userMapper.selectUserById(teacherId);

            for (int userId : user_ids) {

                //添加group的信息
                Group group = new Group();

                group.setTag(tag);
                group.setTagId(term_tag.getId());
                group.setTeacherId(teacher.getId());
                group.setTeacher(teacher.getUserName());
                group.setStudentId(userId);
                //查询学生的姓名
                User student = userMapper.selectUserById(userId);
                group.setStudent(student.getUserName());
                groupMapper.addGroup(group);
            }
            //返回
            return ResponseResult.ok().put("tag", tag).put("teacher", teacherId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }


}

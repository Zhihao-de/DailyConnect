package cn.ac.iscas.service.tag;

import cn.ac.iscas.dao.GroupMapper;
import cn.ac.iscas.dao.StudentlistMapper;
import cn.ac.iscas.dao.TagMapper;
import cn.ac.iscas.entity.Group;
import cn.ac.iscas.entity.Tag;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TagRetrievalService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 12 标签管理页面 根据老师的id来查询他创建的所有标签
     * 查询根据每一个group下的所以信息
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult getTagByTeacher(int teacherId) {
        try {
            Tag[] tags = tagMapper.selectTagByTeacher(teacherId);

            if (tags == null || tags.length == 0) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing tags");
            }
            return ResponseResult.ok().put("tag", tags);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }

    }


    /**
     * 根据tagID查询tag
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult getTagById(int id) {
        try {
            Tag tag = tagMapper.selectTagById(id);
            if (tag == null) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing tag");
            }

            return ResponseResult.ok().put("tag", tag);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }


    /**
     * 根据tag的ID 查询所有的taginfo tag 下所有学生的信息 这些事件中都要包含teacherid
     * 15 标签
     * 在标签编辑页面 查看 根据tag的id 查看所有的学生列表  与 拥有该标签的学生
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private StudentlistMapper studentlistMapper;


    public ResponseResult getTagInfo(int id) {
        try {
            //在tag表中根据tag的ID取tag的信息
            Tag tag = tagMapper.selectTagById(id);
            if (tag == null) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing tag");
            }

            //在group中取与学生的关联关系
            //在group的每一条信息中都包含tagID,tag名称，老师的ID，老师的姓名，家长的ID，姓名
            Group[] tis = groupMapper.selectByTagId(id);

            return ResponseResult
                    .ok()
                    .put("tagName", tag.getTag())
                    .put("studentsWithTag", tis);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

}
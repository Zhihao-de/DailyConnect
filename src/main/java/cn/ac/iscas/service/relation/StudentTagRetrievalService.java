package cn.ac.iscas.service.relation;

import cn.ac.iscas.dao.GroupMapper;
import cn.ac.iscas.dao.TagMapper;
import cn.ac.iscas.entity.Group;
import cn.ac.iscas.entity.Tag;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentTagRetrievalService {

    @Autowired
    private GroupMapper groupMapper;
    @Autowired
    private TagMapper tagMapper;

    /**
     * 老师查找学生所有的tags
     *
     * @param studentId
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult RetrievalStudentTag(Integer studentId) {
        try {

            Group[] groups = groupMapper.selectByStudent(studentId);
            Tag[] tag_list;
            if (groups != null && groups.length != 0) {
                tag_list = new Tag[groups.length];
                for (int i = 0; i < groups.length; i++) {
                    Tag term = tagMapper.selectTagById(groups[i].getTagId());
                    tag_list[i] = term;
                }
            } else {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing tags");
            }

            //返回所有tag信息
            return ResponseResult.ok().put("tags", tag_list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }

    }
}

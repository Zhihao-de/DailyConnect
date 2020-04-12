package cn.ac.iscas.service.relation;

import cn.ac.iscas.dao.StudentlistMapper;
import cn.ac.iscas.dao.TagMapper;
import cn.ac.iscas.entity.Studentlist;
import cn.ac.iscas.entity.Tag;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentTagRetrievalService {

    @Autowired
    private StudentlistMapper stuMapper;
    @Autowired
    private TagMapper tagMapper;

    /**
     * 查找学生所有的tags
     *
     * @param parentId studentName tag
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult RetrievalStudentTag(Integer parentId) {
        try {

            Studentlist sl = stuMapper.selectByParentId(parentId);
            String tag_info = sl.getTag();
            //所有tag ID 的字符串
            String[] tags_ids = tag_info.split("%");

            Tag[] tag_list = new Tag[tags_ids.length];
            for (int i = 0; i < tags_ids.length; i++) {
                //convert id of string into integer
                tag_list[i] = tagMapper.selectTagById(Integer.valueOf(tags_ids[i]));

            }
            //返回所有tag信息
            return ResponseResult.ok().put("tags", tag_list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }

    }
}

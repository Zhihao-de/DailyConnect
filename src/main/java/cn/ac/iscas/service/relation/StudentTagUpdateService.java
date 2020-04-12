package cn.ac.iscas.service.relation;

import cn.ac.iscas.dao.StudentlistMapper;
import cn.ac.iscas.entity.Studentlist;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentTagUpdateService {

    @Autowired
    private StudentlistMapper stuMapper;

    /**
     * 更新一系列某个老师管理下的学生的
     * 学生的所有tag 均存储在一个字段下 通过“%”分割
     *
     * @param teacherId studentName tag
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult updateStudentTag(int teacherId, int[] parentId, int tagId) {
        try {

            for (int pid : parentId) {
                Studentlist sl = new Studentlist();
                Studentlist sl_info = stuMapper.selectByParentId(pid);
                //get the tag from the record
                String new_tag = sl_info.getTag() + "%" + tagId;
                //set new tag for the record
                sl_info.setTag(new_tag);
                stuMapper.updateTagByTeacherIdAndStudentName(sl_info);
            }

            return ResponseResult.ok();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }
}

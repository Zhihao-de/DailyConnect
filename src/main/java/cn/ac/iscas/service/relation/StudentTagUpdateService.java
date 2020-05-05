package cn.ac.iscas.service.relation;

import cn.ac.iscas.dao.GroupMapper;
import cn.ac.iscas.entity.Group;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentTagUpdateService {

    @Autowired
    private GroupMapper gMapper;

    /**
     * 更新一系列某个老师管理下的学生的tag
     * 直接在group中增加一条记录
     *
     * @param teacherId studentName tag
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult updateStudentTag(int teacherId, int[] userId, int tagId) {
        try {

            for (int pid : userId) {
                Group newGroup = new Group();
                newGroup.setTagId(tagId);
                newGroup.setTeacherId(teacherId);
                newGroup.setStudentId(pid);
                gMapper.addGroup(newGroup);

            }

            return ResponseResult.ok();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }
}

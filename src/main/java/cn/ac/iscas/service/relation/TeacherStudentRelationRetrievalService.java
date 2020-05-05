package cn.ac.iscas.service.relation;

import cn.ac.iscas.dao.StudentsMapper;
import cn.ac.iscas.entity.Students;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherStudentRelationRetrievalService {

    @Autowired
    private StudentsMapper stuMapper;

    /**
     * 2 Students
     * 根据老师id获取其学生的信息（家长的ID与学生的姓名）.
     *
     * @param teacherId
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */

    public ResponseResult selectStudentsByTeacherId(int teacherId) {
        assert 0 < teacherId;
        try {
            Students[] sls = stuMapper.selectStudentsByTeacherId(teacherId);
            if (null == sls) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing user");
            }
            JSONObject[] ret = new JSONObject[sls.length];

            for (int i = 0; i < sls.length; i++) {
                JSONObject res = new JSONObject();
                res.put("studentName", sls[i].getStudentName());
                ret[i] = res;
            }
            return ResponseResult.ok().put("students", ret);

        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

    /**
     * 根据学生id获取其老师的信息.
     *
     * @param parentId
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    /*public ResponseResult selectTeacherByParentId(int parentId) {
        assert 0 < parentId;
        try {

            Students sl = stuMapper.selectTeacherByParentId(parentId);

            if (null == sl) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing user");
            }

            return ResponseResult.ok().put("teacher", sl);
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
       }
     */


}

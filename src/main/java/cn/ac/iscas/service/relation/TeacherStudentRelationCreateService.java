package cn.ac.iscas.service.relation;

import cn.ac.iscas.dao.StudentsMapper;
import cn.ac.iscas.dao.UserMapper;
import cn.ac.iscas.entity.Students;
import cn.ac.iscas.entity.User;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import cn.ac.iscas.entity.Guardianship;

@Service
public class TeacherStudentRelationCreateService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private StudentsMapper stuMapper;
    //   @Autowired
    //   private GuardianshipMapper pMapper;

    /**
     * 获取用户信息.
     *
     * @param teacherId&parentId
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */

    public ResponseResult create(Integer teacherId, Integer parentId) {
        try {
            //query student name
            //Guardianship pr = pMapper.selectKidByParentId(parentId);
            User parent = userMapper.selectUserById(parentId);
            User teacher = userMapper.selectUserById(teacherId);

            //set table information
            Students sl = new Students();
            sl.setId(parent.getId());
            sl.setId(teacher.getId());
            //sl.setParentName(parent.getUserName());
            //sl.setStudentName(pr.getStudent());
            //System.out.println(pr.getStudent());
            sl.setTeacherName(teacher.getUserName());
            stuMapper.addTeaStuShip(sl);
            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }

    }
}

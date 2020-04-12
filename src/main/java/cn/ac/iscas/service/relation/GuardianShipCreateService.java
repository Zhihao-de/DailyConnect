package cn.ac.iscas.service.relation;

import cn.ac.iscas.dao.GuardianshipMapper;
import cn.ac.iscas.dao.UserMapper;
import cn.ac.iscas.entity.Guardianship;
import cn.ac.iscas.entity.User;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardianShipCreateService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private GuardianshipMapper pMapper;

    /**
     * 增加监护关系（父母与孩子）.
     *
     * @param parentId, stuName
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult create(Integer parentId, String stuName) {
        assert 0 < parentId;
        assert null != stuName;
        try {
            Guardianship p = new Guardianship();
            User parent = userMapper.selectUserById(parentId);
            p.setParentId(parent.getId());
            p.setParent(parent.getUserName());
            p.setStudent(stuName);
            pMapper.addGuardianship(p);
            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }


    }
}

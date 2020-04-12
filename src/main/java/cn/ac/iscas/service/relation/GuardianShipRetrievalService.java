package cn.ac.iscas.service.relation;

import cn.ac.iscas.dao.GuardianshipMapper;
import cn.ac.iscas.entity.Guardianship;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuardianShipRetrievalService {

    @Autowired
    private GuardianshipMapper guardianshipMapper;

    /**
     * 获取学生姓名信息.
     *
     * @param parentId 用户id
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */

    public ResponseResult getStundentName(int parentId) {
        assert 0 < parentId;
        try {
            Guardianship parent = guardianshipMapper.selectKidByParentId(parentId);

            if (null == parent) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing user");
            }
            return ResponseResult.ok().put("student", parent.getStudent());
        } catch (Exception ex) {
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }
}

package cn.ac.iscas.service.tag;

import cn.ac.iscas.dao.GroupMapper;
import cn.ac.iscas.dao.TagMapper;
import cn.ac.iscas.entity.Group;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TagDeleteService {

    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private GroupMapper groupMapper;


    /**
     * 13 标签主页面——删除标签
     * 一次删除一个标签列表中的所有学生，并且删除 标签本身
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */

    public ResponseResult deleteTag(int tagId) {
        try {
            //删除标签下的所有学生
            groupMapper.deleteByTagId(tagId);
            //删除该标签
            tagMapper.deleteTagById(tagId);
            //Group[] groups = groupMapper.selectByTagId(tagId);
            return ResponseResult.ok();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }


    /**
     * 16  编辑标签页——更新
     *
     * 这个要改
     * 次删除一个列表中的某个学生
     *
     * @param id 为 该条group记录的id
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */
    public ResponseResult updateMember(int id, int teacherId, int[] userIds) {
        try {
            assert 0 < id;
            assert 0 < teacherId;
            assert 0 < userIds.length;

            //删除该tag下的所有学生的信息 并获取 parentId
            groupMapper.deleteByTagId(id);
            //重新新增user的tag信息
            for (int userId : userIds) {
                Group g = new Group();

            }
            return ResponseResult.ok();
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }


}

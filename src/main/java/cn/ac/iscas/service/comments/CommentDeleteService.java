package cn.ac.iscas.service.comments;

import cn.ac.iscas.dao.CommentsMapper;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentDeleteService {

    @Autowired
    private CommentsMapper cMapper;

    /**
     * 删除一条评论
     *
     * @param
     */
    public ResponseResult delete(int commentId) {
        try {
            assert 0 < commentId;
            cMapper.deleteCommentById(commentId);
            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }
}

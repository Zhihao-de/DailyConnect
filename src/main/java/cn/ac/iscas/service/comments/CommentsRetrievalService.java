package cn.ac.iscas.service.comments;

import cn.ac.iscas.dao.CommentsMapper;
import cn.ac.iscas.entity.Comments;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentsRetrievalService {
    @Autowired
    private CommentsMapper cMapper;

    /**
     * 7 某作业的详细页 评论区
     * 根据一事件的ID 查询一条评论所有信息
     *
     * @param
     */
    public ResponseResult queryCommentsByTitleId(int titleId) {
        try {
            assert 0 < titleId;
            Comments[] coms = cMapper.selectCommentsByTitleId(titleId);
            if (coms == null) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing comment");
            }
            return ResponseResult.ok().put("comments", coms);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

    /**
     * 根据一评论的ID 查询一条评论所有信息
     *
     * @param
     */
    public ResponseResult queryCommentsById(int commentId) {
        try {
            assert 0 < commentId;
            Comments com = cMapper.selectCommentById(commentId);
            if (com == null) {
                return ResponseResult.error(ErrCodes.EMPTY_RESULT_SET, "non-existing comment");
            }
            return ResponseResult.ok().put("comment", com);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }
}


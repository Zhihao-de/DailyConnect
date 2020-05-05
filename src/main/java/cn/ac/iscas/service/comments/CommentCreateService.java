package cn.ac.iscas.service.comments;

import cn.ac.iscas.dao.CommentsMapper;
import cn.ac.iscas.entity.Comments;
import cn.ac.iscas.error.ErrCodes;
import cn.ac.iscas.service.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentCreateService {

    @Autowired
    private CommentsMapper cMapper;

    /**
     * 9 作业的详细页 提交新的评论
     * 创建一条评论
     *
     * @param
     * @return ok或<code>ErrCodes.EMPTY_RESULT_SET</code>或<code>ErrCodes.SERVICE_UNEXPECTED_ERROR</code>
     */

    public ResponseResult create(int titleId, int uid, int tid, String content, Date commentTime) {
        try {

            assert 0 < titleId;
            assert 0 < uid;
            assert 0 < tid;
            assert null != content;
            assert null != commentTime;

            Comments comment = new Comments();
            comment.setTid(tid);
            comment.setUid(uid);
            comment.setTitleId(titleId);
            comment.setContent(content);
            comment.setCommentTime(commentTime);

            cMapper.addComment(comment);
            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }

    public ResponseResult create(Comments cm) {
        try {

            assert 0 < cm.getTitleId();
            assert 0 < cm.getTid();
            assert 0 < cm.getUid();
            assert null != cm.getContent();
            assert null != cm.getCommentTime();

            cMapper.addComment(cm);
            return ResponseResult.ok();

        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseResult.error(ErrCodes.SERVICE_UNEXPECTED_ERROR, ex.getMessage());
        }
    }


}

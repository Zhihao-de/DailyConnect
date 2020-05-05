package cn.ac.iscas.controller;


import cn.ac.iscas.entity.Comments;
import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.comments.CommentCreateService;
import cn.ac.iscas.service.comments.CommentDeleteService;
import cn.ac.iscas.service.comments.CommentsRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;


@RestController
@RequestMapping(value = "/comment")
@Validated
public class CommentsController extends ControllerBase {
    @Autowired
    private CommentCreateService ccs;

    /**
     * 9
     * 提交新的评论
     * para comments
     * return ok
     */
    // create a comment
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseResult create(
            @RequestBody Comments comments
    ) {
        assert null != comments;
        return ccs.create(comments);
    }


    @Autowired
    private CommentsRetrievalService crs;

    //query a comment by commentId
    @RequestMapping(value = "/query/{cid}", method = RequestMethod.GET)
    public ResponseResult queryCommentById(
            @PathVariable("cid") @Min(value = 1, message = "invalid_comment_id") int commentId
    ) {
        return crs.queryCommentsById(commentId);
    }

    /**
     * 7
     * 作业的评论区 titleId是作业的Id
     * para titleID
     * return comments info
     */
    //query comments by titleId
    @RequestMapping(value = "/{tid}", method = RequestMethod.GET)
    public ResponseResult queryCommentsByTitleId(
            @PathVariable("tid") @Min(value = 1, message = "invalid_title_id") int titleId
    ) {
        return crs.queryCommentsByTitleId(titleId);
    }

    @Autowired
    private CommentDeleteService cds;

    //delete a comment by id
    @RequestMapping(value = "/delete/{cid}", method = RequestMethod.DELETE)
    public ResponseResult delete(
            @PathVariable("cid") @Min(value = 1, message = "invalid_comment_id") int commentId
    ) {
        return cds.delete(commentId);
    }

}

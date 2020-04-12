package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Comments;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsMapper {
    //add a comment
    int addComment(Comments record);

    //select comment by id
    Comments selectCommentById(Integer id);

    //select comment by  title id
    Comments[] selectCommentsByTitleId(Integer titleId);

    //delete comment by id
    int deleteCommentById(Integer id);

    Comments selectCommentByUid(Integer uid);

    Comments selectCommentByTid(Integer tid);
    /*
    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
    */
}
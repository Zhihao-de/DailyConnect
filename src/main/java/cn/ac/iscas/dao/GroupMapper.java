package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Group;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMapper {
    //select a single record by id
    Group selectById(Integer id);

    //select info by tag id
    Group[] selectByTagId(Integer tagId);

    //delete group by tag id
    int deleteByTagId(Integer tagId);

    //add tag of group
    int addGroup(Group record);


    //delete group member by ID
    int deleteById(Integer id);
}
package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Tag;

public interface TagMapper {

    //add a new tag
    int addTag(Tag record);

    //query tag by teacher Id
    Tag[] selectTagByTeacher(Integer teacher);

    //query tag by id
    Tag selectTagById(Integer id);

    //delete a tag
    int deleteTagById(Integer id);

    //query info by tag name
    Tag selectTagByName(String tag);
}

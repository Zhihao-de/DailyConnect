package cn.ac.iscas.dao;

import cn.ac.iscas.entity.Studentlist;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentlistMapper {


    //add  teacher-student relationship
    int addTeaStuShip(Studentlist record);

    //select student name by teacher id
    Studentlist[] selectStudentsByTeacherId(Integer id);

    //select teacher name by parent id
    Studentlist selectTeacherByParentId(Integer id);

    //update student tag
    int updateTagByTeacherIdAndStudentName(Studentlist Record);

    Studentlist[] selectStudentsByTag(String tag);

    //select info by parentId
    Studentlist selectByParentId(Integer parentId);


    Studentlist selectByTeacherIdAndStudentName(Studentlist Record);


}
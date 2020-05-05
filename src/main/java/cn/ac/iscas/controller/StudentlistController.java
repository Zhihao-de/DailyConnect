package cn.ac.iscas.controller;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.relation.TeacherStudentRelationCreateService;
import cn.ac.iscas.service.relation.TeacherStudentRelationRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "/students")
@Validated

public class StudentlistController extends ControllerBase {

    @Autowired
    private TeacherStudentRelationCreateService tsrcs;

    @RequestMapping(value = "/tsr/create", method = RequestMethod.POST)
    //add student list of a teacher
    public ResponseResult create(
            @RequestParam @Min(value = 1, message = "invalid_user_id") int parentId,
            @RequestParam @Min(value = 1, message = "invalid_user_id") int teacherId
    ) {
        return tsrcs.create(teacherId, parentId);
    }


    @Autowired
    private TeacherStudentRelationRetrievalService tsrrs;

    /**
     * 2
     * 老师查询学生列表
     * para id 老师的ID
     * return 学生的列表
     */
    @RequestMapping(value = "/tsr/teacher/{tid}", method = RequestMethod.GET)
    //query student list of a teacher
    public ResponseResult queryStudentList(
            @PathVariable("tid") @Min(value = 1, message = "invalid_teacher_userId") int teacherId
    ) {
        return tsrrs.selectStudentsByTeacherId(teacherId);
    }

}

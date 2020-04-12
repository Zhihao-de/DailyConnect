package cn.ac.iscas.controller;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.tag.TagCreateService;
import cn.ac.iscas.service.tag.TagDeleteService;
import cn.ac.iscas.service.tag.TagRetrievalService;
import cn.ac.iscas.service.tag.TagUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "/tag")
@Validated
public class TagController extends ControllerBase {

    @Autowired
    private TagCreateService tcs;
    //这个可以

    /**
     * 14
     * 创建标签
     * para  tagname  group
     * return ok
     */

    @RequestMapping(value = "/{tid}/create", method = RequestMethod.POST)
    //create tag
    public ResponseResult create(
            @PathVariable("tid") @Min(value = 1, message = "invalid_user_id") int teacherId,
            @RequestParam String tagName,
            @RequestBody int[] parentIds

    ) {
        return tcs.CreateGroup(teacherId, tagName, parentIds);
    }

    @Autowired
    private TagRetrievalService trs;

    /**
     * 12
     * 标签管理主页  查看所有的标签
     * para 老师的ID
     * return tag info  包含所有tag的名称
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    //get tag by teacherID
    public ResponseResult getByTeacher(
            @RequestParam Integer teacherId
    ) {
        return trs.getTagByTeacher(teacherId);
    }


    @RequestMapping(value = "/{tagId}", method = RequestMethod.GET)
    //get tag by tagID
    public ResponseResult get(
            @PathVariable("tagId") @Min(value = 1, message = "invalid_tag_id") int tagId
    ) {
        return trs.getTagById(tagId);
    }


    @RequestMapping(value = "/query", method = RequestMethod.GET)
    //get tag by tagID
    public ResponseResult getList(

            @RequestParam int tagId
    ) {
        return trs.getTagInfo(tagId);
    }


    @Autowired
    private TagUpdateService tus;

    /**
     * 14
     * 标签主页面 更新标签
     * para  tag ID
     * return ok
     */
    @RequestMapping(value = "update/{tagId}/{teacherId}", method = RequestMethod.PATCH)
    //delete tag
    public ResponseResult update(
            @PathVariable("tagId") @Min(value = 1, message = "invalid_tag_id") int tagId,
            @PathVariable("teacherId") @Min(value = 1, message = "invalid_teacher_id") int teacherId,
            @RequestBody int[] parentIds
    ) {

        return tus.updateMember(tagId, teacherId, parentIds);
    }


    @Autowired
    private TagDeleteService tds;

    /**
     * 13
     * 标签主页面 删除标签
     * para  tag ID
     * return ok
     */
    @RequestMapping(value = "/delete/{tagId}", method = RequestMethod.DELETE)
    //delete tag

    public ResponseResult delete(
            @PathVariable("tagId") @Min(value = 1, message = "invalid_tag_id") int tagId
    ) {
        return tds.deleteTag(tagId);
    }


}

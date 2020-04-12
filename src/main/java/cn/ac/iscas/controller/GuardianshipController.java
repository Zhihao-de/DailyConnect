package cn.ac.iscas.controller;

import cn.ac.iscas.service.ResponseResult;
import cn.ac.iscas.service.relation.GuardianShipCreateService;
import cn.ac.iscas.service.relation.GuardianShipRetrievalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequestMapping(value = "/guard")
@Validated
public class GuardianshipController {

    @Autowired
    private GuardianShipCreateService gcs;

    // create guardianship
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseResult create(
            @RequestParam @Min(value = 1, message = "invalid_user_id") int parentId,
            @RequestParam String kidName
    ) {
        return gcs.create(parentId, kidName);
    }


    @Autowired
    private GuardianShipRetrievalService grs;

    //retrieval guardianship find kid's name
    @RequestMapping(value = "/{pid}", method = RequestMethod.GET)
    public ResponseResult get(
            @PathVariable("pid") @Min(value = 1, message = "invalid_user_id") int parentId
    ) {
        return grs.getStundentName(parentId);
    }

}

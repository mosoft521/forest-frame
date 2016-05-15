package com.dempe.forest.manger.controller;

import com.alibaba.fastjson.JSONObject;
import com.dempe.forest.manger.model.Role;
import com.dempe.forest.manger.model.User;
import com.dempe.forest.manger.service.RoleService;
import com.dempe.forest.manger.utils.JSPForward;
import com.dempe.forest.manger.utils.JsonResult;
import com.google.common.base.Strings;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2016/5/15 0015.
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("index")
    public String index() {
        return JSPForward.ROLE.path();
    }

    @RequestMapping("list")
    @ResponseBody
    public List<Role> list() {
        return roleService.list();
    }

    @RequestMapping("save")
    @ResponseBody
    public JSONObject save(@ModelAttribute Role role) {
        String id ="";
        if(!Strings.isNullOrEmpty(role.getId())){
            id= role.getId();
        }else {
            id = new ObjectId().toString();
        }
        role.setId(id);
        roleService.save(role);
        return JsonResult.getJsonResult();
    }

    @RequestMapping("getById")
    @ResponseBody
    public Role getById(@RequestParam String id) {
        return roleService.getById(id);
    }

    @RequestMapping("delById")
    @ResponseBody
    public JSONObject delById(@RequestParam String id) {
        roleService.deleteById(id);
        return JsonResult.getJsonResult();

    }
}

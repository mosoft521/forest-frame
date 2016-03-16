package com.dempe.forest.manger.controller;

import com.alibaba.fastjson.JSONObject;
import com.dempe.forest.manger.model.User;
import com.dempe.forest.manger.service.UserService;
import com.dempe.forest.manger.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/16
 * Time: 14:01
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("index")
    public String add(Model model) {
        model.addAttribute("userList", userService.listUser());
        return "/user/index";
    }

    @RequestMapping("login")
    public String login(Model model) {
        return "/login";
    }


    @RequestMapping("/new")
    public String newApp() {
        return "/user/newUser";
    }

    @RequestMapping("/del")
    @ResponseBody
    public JSONObject delUser(@RequestParam String uid) {
        userService.delUser(uid);
        return JsonResult.getJsonResult();
    }

    @RequestMapping("/save")
    public String saveUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/app/index";
    }


}

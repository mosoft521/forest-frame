package com.dempe.forest.manger.controller;

import com.alibaba.fastjson.JSONObject;
import com.dempe.forest.manger.model.User;
import com.dempe.forest.manger.service.UserService;
import com.dempe.forest.manger.utils.JSPForward;
import com.dempe.forest.manger.utils.JsonResult;
import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    @Autowired
    @Qualifier("org.springframework.security.authenticationManager")
    protected AuthenticationManager authenticationManager;

    @RequestMapping("index")
    public String index() {
        return JSPForward.USER.path();
    }

    @RequestMapping("list")
    @ResponseBody
    public List<User> list() {
        List<User> userList = userService.list();
        return buildUserList(userList);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public User getById(@RequestParam String id) {
        return userService.getById(id);
    }

    @RequestMapping("/delById")
    @ResponseBody
    public JSONObject delById(@RequestParam String id) {
        userService.delById(id);
        return JsonResult.getJsonResult();
    }

    @RequestMapping("/register")
    public String register(HttpServletRequest request, @ModelAttribute User user) {
        user.setRoleId(1);
        userService.save(user);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getName(), user.getPwd());
        //request.getSession();
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticatedUser = authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return "redirect:/forest/index";
    }


    @RequestMapping("/save")
    @ResponseBody
    public String save(@ModelAttribute User user) {
        String id = "";
        if (Strings.isNullOrEmpty(user.getUid())) {
            id = new ObjectId().toString();
        }
        user.setUid(id);
        userService.save(user);
        return JsonResult.getJsonResult().toJSONString();
    }

    /**
     * 设置用户角色
     *
     * @param userList
     * @return
     */
    private List<User> buildUserList(List<User> userList) {
        for (User user : userList) {
            if (user.getRoleId() == User.RoleType.ROLE_ADMIN.value()) {
                user.setRoleName("管理员");
            } else if (user.getRoleId() == User.RoleType.ROLE_MANGER.value()) {
                user.setRoleName("超级管理员");
            } else if (user.getRoleId() == User.RoleType.ROLE_USER.value()) {
                user.setRoleName("普通用户");
            }
        }
        return userList;
    }


}

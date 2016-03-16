package com.dempe.forest.manger.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: Dempe
 * Date: 2016/3/16
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("forest")
public class ForestController {

    @RequestMapping("index")
    public String index() {
        System.out.println("index============================");
        return "index";

    }
}

package com.smt.pc.Interface.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController
 *
 * @author LIJIKAI
 * @date 18/4/2
 */
@Controller
public class IndexController {



    @RequestMapping("/test")
    public String test(){


//        EmailService.sendSimpleEmail("我是标题","我晕");
        return "test";
    }


    @RequestMapping("/")
    public String idx() {
        return "index";
    }


}

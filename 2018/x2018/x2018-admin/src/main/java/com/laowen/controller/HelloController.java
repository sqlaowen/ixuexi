package com.laowen.controller;

import com.laowen.entity.SysUser;
import com.laowen.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/hello")
    public String hello(Model model){
//        SysUser sysUser = sysUserService.findById("e2e904211f4311e6a2d6185e0f846dee");
//        System.out.println(sysUser);
        model.addAttribute("msg","hello world");
        return "/hello";
    }

    @ResponseBody
    @RequestMapping("/json")
    public SysUser json(){
        SysUser sysUser = sysUserService.findById("e2e904211f4311e6a2d6185e0f846dee");
        return sysUser;
    }

}

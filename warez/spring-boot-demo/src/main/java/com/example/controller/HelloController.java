package com.example.controller;

import com.example.PropertyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by iyou on 2017/4/15.
 * Controller返回视图
 */
@Controller
public class HelloController {

    @RequestMapping("/{name}")
    public String index(@PathVariable("name") String name, Model model){
        System.out.println(PropertyUtil.getPropertyByKey("wxId"));
        System.out.println("------------------------------------------------");
        model.addAttribute("name","world");
        return "index";
    }
}

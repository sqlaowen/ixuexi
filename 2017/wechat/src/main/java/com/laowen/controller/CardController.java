package com.laowen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by iyou on 2017-06-25.
 * 卡片管理
 */
@Controller
@RequestMapping("/card")
public class CardController {

    //设备管理
    @RequestMapping("/device")
    public String device() {
        return "/card/device";
    }

    //我的卡片
    @RequestMapping("/me")
    public String me() {
        return "/card/me";
    }

}

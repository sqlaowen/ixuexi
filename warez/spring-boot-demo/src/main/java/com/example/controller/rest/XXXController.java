package com.example.controller.rest;

import com.example.bean.WXGetway;
import com.example.service.WXGetwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iyou on 2017/4/15.
 * RestController直接返回json
 */
@RestController
public class XXXController {

    @Autowired
    private WXGetwayService wxGetwayService;

    @RequestMapping("/rest")
    public WXGetway index() {
        WXGetway wxGetway = wxGetwayService.findByGhid("gh_d306845639cb");
        if (null == wxGetway) {
            wxGetway = new WXGetway() {{
                setWxId("1");
            }};
        }
        return wxGetway;
    }
}

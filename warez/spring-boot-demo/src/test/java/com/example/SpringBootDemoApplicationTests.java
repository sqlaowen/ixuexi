package com.example;

import com.example.bean.WXGetway;
import com.example.mapper.WXGetwayMapper;
import com.example.service.WXGetwayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDemoApplicationTests {

    @Autowired
    private WXGetwayService wxGetwayService;

    @Autowired
    private WXGetwayMapper wxGetwayMapper;

    @Test
    public void contextLoads() {
        WXGetway wxGetway = wxGetwayMapper.findByGhid("gh_d306845639cb");
        System.out.println("-----------------------------------------------------");
        System.out.println(wxGetway);
        System.out.println("-----------------------------------------------------");
        wxGetway = wxGetwayService.findByGhid("gh_d306845639cb");
        System.out.println(wxGetway);
        System.out.println("-----------------------------------------------------");
    }

}

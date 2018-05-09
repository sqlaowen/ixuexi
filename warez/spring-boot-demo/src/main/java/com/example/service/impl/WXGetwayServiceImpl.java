package com.example.service.impl;

import com.example.bean.WXGetway;
import com.example.mapper.WXGetwayMapper;
import com.example.service.WXGetwayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by iyou on 2017/4/15.
 */
@Service
@Transactional
public class WXGetwayServiceImpl implements WXGetwayService {

    @Autowired
    private WXGetwayMapper wxGetwayMapper;

    @Override
    public WXGetway findByGhid(String ghid) {
        return wxGetwayMapper.findByGhid(ghid);
    }
}

package com.example.service;

import com.example.bean.WXGetway;

/**
 * Created by iyou on 2017/4/15.
 */
public interface WXGetwayService {
    /**
     * 通过原始id查询
     *
     * @param ghid
     * @return
     */
    WXGetway findByGhid(String ghid);
}

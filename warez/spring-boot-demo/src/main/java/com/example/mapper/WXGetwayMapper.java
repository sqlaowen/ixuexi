package com.example.mapper;

import com.example.bean.WXGetway;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WXGetwayMapper {

    /**
     * 通过原始id查询
     *
     * @param ghid
     * @return
     */
    WXGetway findByGhid(String ghid);


}
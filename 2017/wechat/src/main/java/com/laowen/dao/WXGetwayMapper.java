package com.laowen.dao;

import com.laowen.bean.WXGetway;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WXGetwayMapper {

    int insertOne(WXGetway wxGetway);

    /**
     * 通过id查询
     *
     * @param wxId
     * @return
     */
    WXGetway findById(String wxId);

    /**
     * 通过原始id查询
     *
     * @param ghid
     * @return
     */
    WXGetway findByGhid(String ghid);


    /**
     * 通过appid和系统分配的companyId查询
     *
     * @param appId
     * @param companyId
     * @return
     */
    WXGetway findByAppIdAndCompanyId(@Param("appId") String appId, @Param("companyId") String companyId);

    int updateById(WXGetway wxGetway);

}
package com.laowen.dao;

import com.laowen.bean.WXFans;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WXFansMapper {

    int insertOne(WXFans wxFans);

    /**
     * 通过原始id和openId查询
     *
     * @param wxGhid
     * @param openId
     * @return
     */
    WXFans findByGhidAndOpenId(@Param("wxGhid") String wxGhid, @Param("openId") String openId);

    /**
     * 根据openId修改用户状态
     *
     * @param openId
     * @param fansState
     * @return
     */
    int updateStateById(@Param("openId") String openId, @Param("fansState") Integer fansState);

}
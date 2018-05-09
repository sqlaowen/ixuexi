package com.laowen.mapper;

import com.laowen.entity.WXFans;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface WXFansMapper {

    /**
     * @param openId
     * @return
     */
    @Transactional
    WXFans findByOpenId(@Param("openId") String openId);

}
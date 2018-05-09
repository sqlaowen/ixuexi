package com.laowen.dao;

import com.laowen.bean.FansRefCard;
import com.laowen.bean.domain.CMOIT2003Dto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FansRefCardMapper {

    /**
     * 保存
     *
     * @param fansRefCard
     * @return
     */
    int insertOne(FansRefCard fansRefCard);

    /**
     * 更新
     *
     * @param fansRefCard
     * @return
     */
    int updateById(FansRefCard fansRefCard);

    /**
     * 通过openId查询
     *
     * @param openId
     * @return
     */
    List<FansRefCard> findByOpenId(String openId);

    /**
     * 通过openId和msisdn/iccid/imsi查询
     *
     * @param openId
     * @param cmoit2003Dto
     * @return
     */
    FansRefCard findByOpenIdAndCmoit2003(@Param("openId") String openId, @Param("cmoit2003Dto") CMOIT2003Dto cmoit2003Dto);

}
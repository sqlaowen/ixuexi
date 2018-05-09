package com.laowen.dao;

import com.laowen.bean.InternetCard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternetCardMapper {

    /**
     * 保存
     *
     * @param internetCard
     * @return
     */
    int insertOne(InternetCard internetCard);

    /**
     * 根据msisdn查找
     *
     * @param msisdn
     * @return
     */
    InternetCard findByMsisdn(String msisdn);

    /**
     * 查找list, 用于获取激活时间
     *
     * @return
     */
    List<InternetCard> findListForFeachJihuoTime(@Param("province") String province);

    /**
     * 更新
     *
     * @param internetCard
     * @return
     */
    int updateById(InternetCard internetCard);

}
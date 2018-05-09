package com.laowen.service;

import com.laowen.bean.InternetCard;

/**
 * Created by iyou on 2017/5/29.
 */
public interface InternetCardService {

    /**
     * @param internetCard
     * @return
     */
    int addOrUpdate(InternetCard internetCard);

    /**
     * 根据msisdn查找
     *
     * @param msisdn
     * @return
     */
    InternetCard findByMsisdn(String msisdn);

}

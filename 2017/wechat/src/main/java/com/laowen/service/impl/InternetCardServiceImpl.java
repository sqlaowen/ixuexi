package com.laowen.service.impl;

import com.laowen.bean.InternetCard;
import com.laowen.dao.InternetCardMapper;
import com.laowen.service.InternetCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by iyou on 2017/5/29.
 */
@Service
public class InternetCardServiceImpl implements InternetCardService {

    @Autowired
    private InternetCardMapper internetCardMapper;

    @Override
    public int addOrUpdate(InternetCard internetCard) {
        return 0;
    }

    @Override
    public InternetCard findByMsisdn(String msisdn) {
        return internetCardMapper.findByMsisdn(msisdn);
    }
}

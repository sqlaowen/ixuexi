package com.laowen.service.impl;

import com.laowen.bean.FansRefCard;
import com.laowen.bean.domain.CMOIT2003Dto;
import com.laowen.dao.FansRefCardMapper;
import com.laowen.service.FansRefCardService;
import com.laowen.utils.RandomGUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by iyou on 2017/5/14.
 */

@Service
public class FansRefCardServiceImpl implements FansRefCardService {

    private Logger log = LoggerFactory.getLogger(FansRefCardServiceImpl.class);

    @Autowired
    private FansRefCardMapper fansRefCardMapper;

    @Transactional
    @Override
    public int addOrUpdate(FansRefCard fansRefCard) {

        FansRefCard card = fansRefCardMapper.findByOpenIdAndCmoit2003(fansRefCard.getOpenId(), new CMOIT2003Dto() {{
            setMsisdn(fansRefCard.getMsisdn());
            setIccid(fansRefCard.getIccid());
            setImsi(fansRefCard.getImsi());
        }});

        fansRefCard.setEditTime(new Date());

        if (null != card) {
            fansRefCard.setCardId(card.getCardId());
            Integer searchNo = card.getSearchNo() == null ? 1 : card.getSearchNo() + 1;
            fansRefCard.setSearchNo(searchNo);
            return fansRefCardMapper.updateById(fansRefCard);
        }
        fansRefCard.setCardId(new RandomGUID().toString());
        fansRefCard.setSearchNo(1);
        fansRefCard.setCreateTime(new Date());
        return fansRefCardMapper.insertOne(fansRefCard);
    }

    @Override
    public List<FansRefCard> findByOpenId(String openId) {
        return fansRefCardMapper.findByOpenId(openId);
    }

}

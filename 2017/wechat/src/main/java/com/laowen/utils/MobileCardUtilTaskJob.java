package com.laowen.utils;

import com.github.pagehelper.PageHelper;
import com.laowen.bean.InternetCard;
import com.laowen.bean.domain.ChinaMobileDto;
import com.laowen.dao.InternetCardMapper;
import com.laowen.service.ChinaMobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by iyou on 2017-07-20.
 */
@Component
public class MobileCardUtilTaskJob {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private InternetCardMapper internetCardMapper;

    @Autowired
    private ChinaMobileService chinaMobileService;

    @Value("${for.jihuo.province.list}")
    private String provinceList;

    public void fetchJihuoTime() {
        String[] provinceArr = provinceList.trim().split(",");
        for (String province : provinceArr) {
            fetchLogic(province);
        }
    }

    //region private method

    //获取激活时间主逻辑
    private void fetchLogic(String province) {
        int pageNum = 1;
        PageHelper.startPage(1, 100);
        List<InternetCard> internetCartList = internetCardMapper.findListForFeachJihuoTime(province);
        PageResult<InternetCard> pageResult = new PageResult<InternetCard>(internetCartList);

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        List<Callable<Integer>> callableList = new ArrayList<>();

        while (pageNum < pageResult.getPages()) {
            PageHelper.startPage(pageNum, 100);
            internetCartList = internetCardMapper.findListForFeachJihuoTime(province);
            for (InternetCard internetCard : internetCartList) {

                callableList.add(() -> {
                    int fetchSuccess = 0;
                    //查询
                    Result<String> cardStatusResult = chinaMobileService.cmiot_api2002(new ChinaMobileDto() {{
                        setMsisdn(internetCard.getMsisdn());
                        setProvince(province);
                    }});

                    InternetCard editCard = new InternetCard();
                    editCard.setMsisdn(internetCard.getMsisdn());
                    editCard.setEditTime(new Date());

                    if (!cardStatusResult.getCode()) {
                        log.warn("chinaMobileService.cmiot_api2002请求失败...,\r\n{}\r\n{}", internetCard, cardStatusResult);
                        return fetchSuccess;
                    }

                    if ("待激".equals(cardStatusResult.getT()) && 0 == internetCard.getJihuoMark()) {
                        editCard.setJihuoMark(1); //已标记
                    } else if (("正常".equals(cardStatusResult.getT()) || "停机".equals(cardStatusResult.getT())) && 0 == internetCard.getJihuoMark()) {
                        editCard.setJihuoMark(3); //之前激活了
                    } else if (("正常".equals(cardStatusResult.getT()) || "停机".equals(cardStatusResult.getT())) && 1 == internetCard.getJihuoMark()) {
                        editCard.setJihuoTime(new Date());
                        editCard.setJihuoMark(2); //激活成功
                    }
                    if ("待激".equals(cardStatusResult.getT()) && 1 == internetCard.getJihuoMark()) {
                        //用户还没有使用激活,什么都不处理
                    } else {
                        log.warn("状态与标记没有判断到,好惭愧...\r\n{}\r\n{}", internetCard, cardStatusResult);
                    }
                    //更新
                    fetchSuccess = internetCardMapper.updateById(editCard);
                    return fetchSuccess;
                });

                try {
                    List<Future<Integer>> futures = executorService.invokeAll(callableList);
                    for (Future<Integer> future : futures) {
                        future.get();
                    }
                } catch (Exception e) {
                    log.error("MobileCardUtilTaskJob executorService.invokeAll error", e);
                }
            }
            pageNum++;
        }
        executorService.shutdown();

    }
    //endregion

}

package com.warez;

import com.warez.service.GrabPriceService;
import com.warez.service.impl.GrabPriceServiceImpl;
import com.warez.utils.HttpReqUtil;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 */
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("notenv/applicationContext.xml");
        GrabPriceService grabPriceService = context.getBean(GrabPriceService.class);

        String firstDayOfWeek = DateFormatUtils.format(getWeekStartDate(), "yyyy-MM-dd");
        String currentDay = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

        Long startTime = 0L;

        //每周日抓取一下商品
        if (firstDayOfWeek.equals(currentDay)) {
            log.info("每周日开始抓取商品...");
            startTime = new Date().getTime();
            grabPriceService.grabSku();
            log.info("抓取商品耗时 {} 毫秒...", new Date().getTime() - startTime);
        }

        //每天抓取价格
        log.info("开始抓取价格...");
        startTime = new Date().getTime();
        grabPriceService.grabPrice();
        log.info("抓取价格耗时 {} 毫秒...", new Date().getTime() - startTime);
        log.info("程序运行完成, 结束!");

    }

    private static Date getWeekStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();
    }
}

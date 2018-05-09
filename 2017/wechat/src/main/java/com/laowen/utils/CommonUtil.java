package com.laowen.utils;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by iyou on 2017/4/20.
 */
public class CommonUtil {

    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);

    /**
     * 获取指定长度的随机数字
     *
     * @param len 长度
     * @return
     */
    public static String getLenNumberStr(Integer len) {
        Integer[] chars = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        StringBuffer stringBuffer = new StringBuffer();
        for (Integer i = 0; i < len; i++) {
            stringBuffer.append(chars[Math.abs(new Random().nextInt() % 10)]);
        }
        return stringBuffer.toString();
    }

    /**
     * 判断某个时间是否在最近七日内
     *
     * @param date
     * @param isIncludeHMS 日期是否包含时分秒, true:包含
     * @return
     */
    public static boolean isInLast7Days(Date date, boolean isIncludeHMS) {
        if (null == date) {
            return false;
        }
        Date currentDate = new Date();
        if (!isIncludeHMS) {
            try {
                currentDate = DateUtils.parseDate(new SimpleDateFormat("yyyyMMdd").format(currentDate), "yyyyMMdd");
            } catch (ParseException e) {
                log.error("日期格式化异常", e);
                return false;
            }
        }
        Date date1 = DateUtils.addDays(currentDate, -8);
        Date date2 = DateUtils.addDays(currentDate, -1);
        if (date.after(date1) && date.before(date2)) {
            return true;
        }
        return false;
    }

}

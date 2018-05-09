package com.warez;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by wenshiwei on 2017/1/16.
 */
public class Text1 {

    public static void main(String[] args) {

        Date date=new Date();
        System.out.println(DateFormatUtils.format(date,"yyyy-MM-dd 00:00:00"));
        System.out.println(DateFormatUtils.format(DateUtils.addDays(date,1),"yyyy-MM-dd 00:00:00"));

        try {
            date.
            DateUtils.parseDate("2017-07-01 10:20:00","yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        StringBuilder sb = new StringBuilder();
//        sb.append("{");
//            sb.append("\"bizcode\": \"pop-finance-center\",");
//            sb.append("\"type\": \"901\",");
//            //sb.append(String.format("\"time\":\"%s\",",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())));
//            //sb.append("\"time\": \""+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())+"\",");
//        sb.append("\"time\":\"" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date()) + "\",");
//            sb.append("\"operator\": \"system\",");
//            sb.append("\"venderidlist\": [");
//                sb.append("{");
//                sb.append("\"venderid\": \"%s\"");
//                sb.append("}");
//            sb.append("],");
//            sb.append("\"property\": {");
//                sb.append("\"statementId\":\"%s\"");
//            sb.append("}");
//        sb.append("}");
//        String str = String.format(sb.toString(),  48461, 300417);
//        System.out.println(str);
    }
}

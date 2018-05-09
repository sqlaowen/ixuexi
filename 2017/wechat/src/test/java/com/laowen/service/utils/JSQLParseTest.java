package com.laowen.service.utils;

import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.lang3.time.DateUtils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wenshiwei on 2017/5/27.
 */
public class JSQLParseTest {

    public static void main(String[] args) throws Exception {

        Date d1= DateUtils.parseDate("2017-06-03","yyyy-MM-dd");
        d1 = DateUtils.addYears(d1,1);
        long days = (d1.getTime() - System.currentTimeMillis()) / 1000 / 60 / 60 / 24;
        System.out.println(days);
        String str="中小流量新10 5 20 3元套餐 元";
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()){
            System.out.println(matcher.group());
            System.out.println(matcher.group());
        }
        String sql = "update popfin0.fin_statement0 set c1=1, modified = now() where id=1";
        Update updateParse = (Update) CCJSqlParserUtil.parse(sql);
//        System.out.println(updateParse.getTable());
//        System.out.println(updateParse.getTable().getSchemaName());
//        System.out.println(updateParse.getTable().getName());
        System.out.println(updateParse.getWhere());
//        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
//        List<String> tableList = tablesNamesFinder.getTableList(updateParse);
//        System.out.println(tableList.get(0));
    }
}

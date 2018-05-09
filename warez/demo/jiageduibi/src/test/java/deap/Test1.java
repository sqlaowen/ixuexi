package deap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshiwei on 2017/3/7.
 */
public class Test1 {

    private static Logger log = LoggerFactory.getLogger(Test1.class);

    public static void main(String[] args) {
        List<String> checkColList = new ArrayList<String>() {{
            add("货款");
            add("佣金");
            add("采购货款");
            add("FCS返点");
            add("退换无忧服务费");
            add("应结货款");
        }};

        System.out.println(checkColList);

        String fileName = "D:/结算单3709618妥投结算数据.csv";
        File file = new File(fileName);
        if (!file.exists()) {
            log.error("校验csv文件,不存在,对应文件名:{}", fileName);
            return;
        }
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String tmp = null;
            reader.mark(10240);
            List<Integer> indexList = null;
            while ((tmp = reader.readLine()) != null) {
                System.out.println(tmp);
                if (StringUtils.isNotBlank(tmp) && 1 < tmp.split(",").length) {
                    indexList = xx(checkColList, tmp.split(","));
                    break;
                }
            }
            System.out.println(indexList);
            reader.reset();
            boolean iscolname = true;
            String[] tmpArr = null;
            BigDecimal[] bigDecimalArr = new BigDecimal[indexList.size()];
            BigDecimal[] lastRowDecimalArr = new BigDecimal[indexList.size()];
            for (int i = 0; i < bigDecimalArr.length; i++) {
                bigDecimalArr[i] = BigDecimal.ZERO;
                lastRowDecimalArr[i]=BigDecimal.ZERO;
            }

            while ((tmp = reader.readLine()) != null) {
                tmpArr = tmp.split(",");
                if (StringUtils.isNotBlank(tmp) && 1 < tmpArr.length && iscolname) {
                    iscolname = false;
                    continue;
                }
                if (StringUtils.isNotBlank(tmp) && 1 < tmpArr.length) {
                    for (int i = 0; i < indexList.size(); i++) {
                        bigDecimalArr[i] = bigDecimalArr[i].add(new BigDecimal(tmpArr[indexList.get(i)]));
                        lastRowDecimalArr[i]=new BigDecimal(tmpArr[indexList.get(i)]);
                    }
                }
            }
            for (int i = 0; i < indexList.size(); i++) {
                if(0==bigDecimalArr[i].divide(BigDecimal.valueOf(2L)).compareTo(lastRowDecimalArr[i])){
                    System.out.println("---success");
                }
            }
            System.out.println("------------------");
        } catch (Exception e) {
            log.error("校验csv文件,读取异常:{},对应文件名:{}", e.getMessage(), fileName);
            return;
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    log.error("校验csv文件,关闭reader异常:{}", e.getMessage());
                }
            }
        }
    }

    static List<Integer> xx(List<String> checkColList, String[] colList) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < checkColList.size(); i++) {
            for (int j = 0; j < colList.length; j++) {
                if (checkColList.get(i).equals(colList[j])) {
                    list.add(j);
                    break;
                }
            }
        }
        return list;
    }

}

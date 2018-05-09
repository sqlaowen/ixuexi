package deap;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenshiwei on 2017/3/1.
 */
public class TestCSV {
    public static void main(String[] args) {
        System.out.println(Boolean.valueOf(""));
//        IService iService=new ServiceImpl();
//        System.out.println(iService.getClass().getSimpleName());
//        if(iService instanceof ServiceImpl2){
//            System.out.println("2的实现");
//        }
//        if(iService instanceof ServiceImpl){
//            System.out.println("1的实现");
//        }

        File file = new File("D:/日帐单156011=2017-02-24妥投销货清单明细.csv");
        file = new File("D:/结算单4287636妥投销货清单明细.csv");
        file=new File("D:/缁撶畻鍗_885284濡ユ姇閿€璐ф竻鍗曟槑缁_csv");
        BufferedReader reader = null;
        List<String[]> stringList = new ArrayList<>();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                System.out.println("当前行:" + tempString);
                if(StringUtils.isNoneBlank(tempString) && !tempString.equals("币种：USD")) {
                    stringList.add(tempString.split(","));
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
            Integer colIndex=findIndex(stringList.get(0),"商品应结金额");
            if(null == colIndex){
                return;
            }

            BigDecimal heji=new BigDecimal(stringList.get(stringList.size()-1)[colIndex]);
            stringList.remove(stringList.size()-1);
            //stringList.remove(0);
            stringList.remove(0);
            BigDecimal dsum=BigDecimal.ZERO;
            for (int i = 0; i < stringList.size(); i++) {
                dsum = dsum.add(new BigDecimal(stringList.get(i)[colIndex]));
            }
            System.out.println("合计金额:"+heji);
            System.out.println("明细加和金额:"+dsum);
           // if(heji.setScale(2, RoundingMode.HALF_EVEN).doubleValue()==dsum.setScale(2, RoundingMode.HALF_EVEN).doubleValue()){
            if(heji.setScale(2, RoundingMode.HALF_EVEN).compareTo(dsum.setScale(2, RoundingMode.HALF_EVEN))==0){
                System.out.println("金额一致, 校验通过");
            } else {
                System.out.println("错误啦...");
            }

    }
    private static Integer findIndex(String [] strArr, String tarStr){
        for (int i = 0; i < strArr.length; i++) {
            if(strArr[i].trim().equals(tarStr.trim())){
                return i;
            }
        }
        return null;
    }
}

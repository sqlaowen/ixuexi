package com.laowen.service.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URL;
import java.util.Collection;

/**
 * Created by iyou on 2017/5/28.
 */
public class DownIMG {

    public static void main(String[] args) throws Exception {

//        Collection<File> files = FileUtils.listFiles(new File("D:/opt/tupian"), new String[]{"jpg"}, false);
//        System.out.println(files.size());
//        Runtime rt = Runtime.getRuntime();
//        String cmd ="D:\\apps\\game\\ImageMagick-7.0.5-Q16\\magick.exe convert %s -colorspace gray -normalize -threshold %s  D:\\opt\\zh\\%s";
//        for (File file : files) {
//            System.out.println(String.format(cmd,file.getAbsolutePath(),"50%",file.getName()));
//            rt.exec(String.format(cmd,file.getAbsolutePath(),"50%",file.getName())).getOutputStream();
//        }
        //rt.exec("D:\\apps\\game\\ImageMagick-7.0.5-Q16\\magick.exe convert D:\\opt\\img.jpg -colorspace gray -normalize -threshold 50%  D:\\opt\\img1.jpg").getOutputStream();


//        Runtime rt = Runtime.getRuntime();
//        for (int i = 0; i < 10; i++) {
//            String url=String.format("http://www.wefundp2p.com/?plugins&q=imgcode&t=0.?%s", System.currentTimeMillis());
//            FileUtils.copyURLToFile(new URL(url),new File("D:/opt/xx/"+i+".jpg"));
//            //rt.exec("D:\\apps\\game\\ImageMagick-7.0.5-Q16\\magick.exe convert D:\\opt\\img.jpg -colorspace gray -normalize -threshold 50%  D:\\opt\\img1.jpg").getOutputStream();
//            rt.exec("D:/apps/game/ImageMagick-7.0.5-Q16/magick.exe convert D:/opt/xx/"+i+".jpg -colorspace gray -threshold 50%  D:/opt/img1.jpg").waitFor();
//            rt.exec("D:/apps/game/Tesseract-OCR/tesseract.exe D:/opt/img1.jpg D:/opt/img").waitFor();
//            String imgtxt =FileUtils.readFileToString(new File("D:/opt/img.txt")).trim();
//            System.out.println("-------------------原字符串"+imgtxt);
//            imgtxt=imgtxt.replace(".","").replace(")","j").replace(">k","x");
//            String yzm= StringUtils.join(imgtxt.split(" "));
//            System.out.println("============================"+yzm);
//            System.out.println("完成第 "+i+" 个");
//        }

        String json="{\"pageInfo\":{\"pageSize\":10,\"pageNum\":1,\"totalRowNum\":1,\"totalPageNum\":0,\"startRowNum\":1,\"endRowNum\":-1},\"exception\":null,\"totalrecords\":1,\"data\":[{\"msisdn\":\"1064832334099\",\"remark\":\"\",\"imsi\":\"\",\"iccId\":\"\",\"status\":\"\",\"netStatus\":\"\",\"gprsStatus\":\"\",\"gprsUsedFlow\":\"\",\"smsUsedFlow\":\"\",\"createDate\":\"\",\"prodName\":\"GPRS中小流量新5元套餐\",\"isSignSms\":\"\",\"isSignGprs\":\"\",\"prodId\":\"I00010100062\",\"prodClass\":\"01\",\"prodStatus\":\"01\",\"statusDate\":\"2016-06-02 18:49:39\",\"balance\":\"\",\"subsId\":\"\",\"prodDescribe\":\"月费：5元，包含30M流量，超出后：流量0.29元/MB，短信0.1元/条\",\"groupId\":\"\",\"groupName\":\"\",\"signSms\":\"\",\"signGprs\":\"\"}]}";
        JSONObject jsonObject = JSON.parseObject(json);
        String kaika =((JSONObject)jsonObject.getJSONArray("data").get(0)).getString("statusDate");
        System.out.println(kaika);
    }
}

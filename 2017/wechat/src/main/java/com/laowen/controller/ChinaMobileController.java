package com.laowen.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laowen.bean.FansRefCard;
import com.laowen.bean.InternetCard;
import com.laowen.bean.WXGetway;
import com.laowen.bean.domain.CMOIT2001Dto;
import com.laowen.bean.domain.CMOIT2003Dto;
import com.laowen.bean.domain.CMOIT2020Dto;
import com.laowen.bean.domain.ChinaMobileDto;
import com.laowen.service.ChinaMobileService;
import com.laowen.service.FansRefCardService;
import com.laowen.service.InternetCardService;
import com.laowen.service.WXGatewayService;
import com.laowen.utils.HttpUtil;
import com.laowen.utils.PropUtil;
import com.laowen.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by iyou on 2017/5/10.
 */

@Controller
@RequestMapping("/internet")
public class ChinaMobileController {

    private Logger log = LoggerFactory.getLogger(ChinaMobileController.class);

    @Autowired
    private WXGatewayService wxGatewayService;

    @Autowired
    private ChinaMobileService chinaMobileService;

    @Autowired
    private FansRefCardService fansRefCardService;

    @Autowired
    private InternetCardService internetCardService;

    //页面
    @RequestMapping("/index")
    public String internet(Model model, HttpServletRequest servletRequest) {
        model.addAttribute("openId", servletRequest.getParameter("openId"));
        return "/chinaMobile/chinaMobile";
    }

    //充值页面
    @RequestMapping("/recharge")
    public String recharge(Model model, HttpServletRequest servletRequest) {
        model.addAttribute("openId", servletRequest.getParameter("openId"));
        return "/chinaMobile/recharge";
    }

    //授权获取openId
    @RequestMapping("/oath2base")
    public String oath2base(HttpServletRequest servletRequest, RedirectAttributes attributes) {
        String appId = PropUtil.getProp("appId");
        String companyId = PropUtil.getProp("companyId");
        Result<WXGetway> getwayResult = wxGatewayService.findByAppIdAndCompanyId(appId, companyId);
        if (!getwayResult.getCode()) {
            log.error("ChinaMobileController.oath2base获取网关失败:{}", getwayResult.getMsg());
            return null;
        }
        String code = servletRequest.getParameter("code");
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                appId, getwayResult.getT().getAppSecret(), code);
        String json = HttpUtil.httpGet(url, null);
        //String json = HttpUtil.sslPost(url, "GET", null, "utf-8");
        JSONObject jsonObject = JSON.parseObject(json);
        String openid = jsonObject.getString("openid");
        attributes.addAttribute("openId", openid);
        return "redirect:/internet/index";
    }

    //查询
    @ResponseBody
    @RequestMapping("/fetch")
    public Map fetch(String openId, String cardNo, String province) {
        Map map = new HashMap();
        map.put("errcode", "0");
        map.put("errmsg", "");

        ChinaMobileDto chinaMobileDto = new ChinaMobileDto();
        chinaMobileDto.setProvince(province);
        if (cardNo.startsWith("1")) {
            chinaMobileDto.setMsisdn(cardNo);
        } else if (cardNo.startsWith("8")) {
            chinaMobileDto.setIccid(cardNo);
        } else if (cardNo.startsWith("4")) {
            chinaMobileDto.setImsi(cardNo);
        } else {
            map.put("errcode", "1");
            map.put("errmsg", "错误:请正确输入\"卡号/ICCID/IMSI\"");
            return map;
        }

        Result<CMOIT2003Dto> result2003 = chinaMobileService.cmiot_api2003(chinaMobileDto);
        if (!result2003.getCode()) {
            map.put("errcode", "1");
            map.put("errmsg", "错误:请正确输入\"卡号/ICCID/IMSI\"");
            return map;
        }

        map.put("msisdn", result2003.getT().getMsisdn());//网卡号
        map.put("imsi", result2003.getT().getImsi());//IMSI
        map.put("iccid", result2003.getT().getIccid());//ICCID
        addOrUpdate(openId, result2003.getT());
        InternetCard internetCard = internetCardService.findByMsisdn(result2003.getT().getMsisdn());
        if (null != internetCard) {
            if (null != internetCard.getKaikaTime()) {
                map.put("kaikaTime", new SimpleDateFormat("yyyy-MM-dd").format(internetCard.getKaikaTime()));
            }
            if (null != internetCard.getJihuoTime()) {
                map.put("jihuoTime", new SimpleDateFormat("yyyy-MM-dd").format(internetCard.getJihuoTime()));
                long daysBetween = (DateUtils.addYears(internetCard.getJihuoTime(), 1).getTime() - System.currentTimeMillis()) / 1000 / 60 / 60 / 24;
                if (0 < daysBetween) {
                    map.put("shengyuTime", daysBetween + " 天");
                } else {
                    map.put("shengyuTime", "待续费");
                }
            } else {
                map.put("jihuoTime", "--");
                map.put("shengyuTime", "--");
            }
        }

        ExecutorService executorService = Executors.newFixedThreadPool(6);
        CountDownLatch countDownLatch = new CountDownLatch(6);
        executorService.execute(() -> {
            Result<String> result2011 = chinaMobileService.cmiot_api2011(chinaMobileDto);
            if (result2011.getCode()) {
                map.put("balance", result2011.getT() + " 元");//余额
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            Result<CMOIT2020Dto> result2020 = chinaMobileService.cmiot_api2020(chinaMobileDto);
            if (result2020.getCode()) {
                map.put("prodname", getGPRSPackage(result2020.getT().getProdname()));//套餐
                map.put("total", result2020.getT().getTotal() + " MB");//套餐总量
                map.put("used", result2020.getT().getUsed() + " KB");//套餐使用总量
                map.put("left", result2020.getT().getLeft() + " KB");//套餐剩余总量
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            Result<String> result2012 = chinaMobileService.cmiot_api2012(chinaMobileDto);
            if (result2012.getCode()) {
                map.put("sms", result2012.getT());//当月短信
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            Result<String> result2002 = chinaMobileService.cmiot_api2002(chinaMobileDto);
            if (result2002.getCode()) {
                map.put("status", result2002.getT());//用户状态
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            Result<String> result2008 = chinaMobileService.cmiot_api2008(chinaMobileDto);
            if (result2008.getCode()) {
                map.put("status2008", result2008.getT());//开关机状态
            }
            countDownLatch.countDown();
        });

        executorService.execute(() -> {
            Result<CMOIT2001Dto> result2001 = chinaMobileService.cmiot_api2001(chinaMobileDto);
            if (result2001.getCode()) {
                map.put("ip", result2001.getT().getIp());//终端IP
                map.put("apn", result2001.getT().getApn());//接入点
                map.put("rat", result2001.getT().getRat());//接入方式
                map.put("gprsStatus", result2001.getT().getGprsstatus());//GPRS在线状态
            }
            countDownLatch.countDown();
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error(String.format("ChinaMobileController.fetch -> countDownLatch.await error, openId:%s, cardNo:%s ", openId, cardNo), e);
        }
        executorService.shutdown();

        return map;
    }

    //保存物联网卡信息
    private void addOrUpdate(String openId, CMOIT2003Dto cmoit2003Dto) {
//        String appId = PropUtil.getProp("appId");
//        String companyId = PropUtil.getProp("companyId");
        String wxId = PropUtil.getProp("wxId");
        FansRefCard internetCard = new FansRefCard();
        internetCard.setOpenId(openId);
        internetCard.setWxId(wxId);
        internetCard.setMsisdn(cmoit2003Dto.getMsisdn());
        internetCard.setIccid(cmoit2003Dto.getIccid());
        internetCard.setImsi(cmoit2003Dto.getImsi());
        fansRefCardService.addOrUpdate(internetCard);
    }

    //1元2MB, 3元10MB, 5元30MB, 10元7MB

    /**
     * 根据套餐名称内的RMB价格解析为对应的流量
     */
    private String getGPRSPackage(String prodname) {
        if (StringUtils.isBlank(prodname)) {
            return "";
        }
        Integer gprs = 0;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(prodname);
        if (matcher.find()) {
            if ("1".equals(matcher.group())) { //1元2MB
                gprs = 2;
            } else if ("3".equals(matcher.group())) { //3元10MB
                gprs = 10;
            } else if ("5".equals(matcher.group())) { //5元30MB
                gprs = 30;
            } else if ("10".equals(matcher.group())) { //10元70MB
                gprs = 70;
            }
        }
        if (0 == gprs) {
            return prodname;
        }
        return gprs + "MB(GPRS流量包)";
    }

}

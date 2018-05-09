package com.laowen.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laowen.bean.WXFans;
import com.laowen.bean.WXGetway;
import com.laowen.bean.WXReceiveMsg;
import com.laowen.dao.WXFansMapper;
import com.laowen.dao.WXGetwayMapper;
import com.laowen.dao.WXReceiveMsgMapper;
import com.laowen.service.WXGatewayService;
import com.laowen.utils.HttpUtil;
import com.laowen.utils.RandomGUID;
import com.laowen.utils.Result;
import com.laowen.wxmsg.ReceiveXmlMsg;
import com.laowen.wxmsg.ResolveReceiveXmlMsgUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;


/**
 * Created by iyou on 2017/4/8.
 */
@Service
public class WXGatewayServiceImpl implements WXGatewayService {

    private Logger log = LoggerFactory.getLogger(WXGatewayServiceImpl.class);

    @Resource
    private WXGetwayMapper wxGetwayMapper;

    @Autowired
    private WXFansMapper wxFansMapper;

    @Autowired
    private WXReceiveMsgMapper wxReceiveMsgMapper;

    @Transactional
    @Override
    public Result<String> getAccessToken(String appId, String companyId) {
        Result<String> result = new Result<String>() {{
            setCode(false);
        }};
        String error = "";
        if (StringUtils.isBlank(appId) || StringUtils.isBlank(companyId)) {
            error = String.format("请求参数不能为空, appId:%s, companyId:%s", appId, companyId);
            log.error(error);
            result.setMsg(error);
            return result;
        }
        WXGetway wxGetway = wxGetwayMapper.findByAppIdAndCompanyId(appId, companyId);
        if (null == wxGetway) {
            error = String.format("请求参数未找到风关, appId:%s, companyId:%s", appId, companyId);
            log.info(error);
            result.setMsg(error);
            return result;
        }
        String accessToken = null;
        Integer expiresIn = 0;
        // 提前200秒抓取新的access_token
        if (null == wxGetway.getAkTimeOut()
                || (new Date()).getTime() >= wxGetway.getAkTimeOut() - 200 * 1000
                || StringUtils.isBlank(wxGetway.getAccessToken())) {
            String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", wxGetway.getAppId(), wxGetway.getAppSecret());
            String json = HttpUtil.sslPost(url, "GET", null, null);

            if (StringUtils.isBlank(json)) {
                error = String.format("从微信端未获取到响应, url:{}, wxGetway:{}", url, wxGetway);
                log.error(error);
                result.setMsg(error);
                return result;

            }
            JSONObject jsonObject = JSON.parseObject(json);
            accessToken = jsonObject.getString("access_token");
            expiresIn = jsonObject.getInteger("expires_in");
            if (StringUtils.isBlank(accessToken) || null == expiresIn) {
                error = String.format("从微信端获取到access_token失败, url:{}, wxGetway:{}, 响应:{}", url, wxGetway, json);
                log.error(error);
                result.setMsg(error);
                return result;
            }

            //更新access_token及超时时间
            WXGetway editWX = new WXGetway();
            editWX.setWxId(wxGetway.getWxId());
            editWX.setAccessToken(accessToken);
            editWX.setAkTimeOut((new Date()).getTime() + expiresIn * 1000);
            editWX.setEditTime(new Date());
            wxGetwayMapper.updateById(editWX);

        } else {
            accessToken = wxGetway.getAccessToken();
        }
        result.setCode(true);
        result.setT(accessToken);
        return result;
    }

    @Override
    public Result<WXGetway> findByAppIdAndCompanyId(String appId, String companyId) {
        Result<WXGetway> result = new Result<WXGetway>() {{
            setCode(false);
        }};

        if (StringUtils.isBlank(appId) || StringUtils.isBlank(companyId)) {
            String error = String.format("请求参数不能为空, appId:%s, companyId:%s", appId, companyId);
            log.error(error);
            result.setMsg(error);
            return result;
        }

        WXGetway wxGetway = wxGetwayMapper.findByAppIdAndCompanyId(appId, companyId);
        result.setCode(true);
        result.setT(wxGetway);
        return result;
    }

    @Transactional
    @Override
    public Result<String> autoAnswer(String xml) {
        Result<String> result = new Result<String>() {{
            setCode(false);
        }};
        if (StringUtils.isBlank(xml)) {
            log.error("请求参数xml不能为空");
            result.setMsg("请求参数xml不能为空");
            return result;
        }
        ReceiveXmlMsg receiveXmlMsg = ResolveReceiveXmlMsgUtil.getMsgEntity(xml);

        WXReceiveMsg wxReceiveMsg = new WXReceiveMsg();
        BeanUtils.copyProperties(receiveXmlMsg, wxReceiveMsg);
        wxReceiveMsg.setWxMsgId(receiveXmlMsg.getMsgId());
        wxReceiveMsg.setOriginalXml(xml);
        wxReceiveMsg.setMsgId(new RandomGUID().toString());
        wxReceiveMsg.setOpenId(receiveXmlMsg.getFromUserName());
        wxReceiveMsg.setWxGhid(receiveXmlMsg.getToUserName());
        wxReceiveMsg.setCreateTime(new Date());
        wxReceiveMsg.setEditTime(new Date());

        wxReceiveMsgMapper.insertOne(wxReceiveMsg);

        if ("event".equalsIgnoreCase(receiveXmlMsg.getMsgType())) { //事件, 保存操作
            if ("subscribe".equalsIgnoreCase(receiveXmlMsg.getEvent()) //订阅事件
                    || "unsubscribe".equalsIgnoreCase(receiveXmlMsg.getEvent())) { //取消订阅事件
                saveWXFans(receiveXmlMsg);

            } else if ("CLICK".equalsIgnoreCase(receiveXmlMsg.getEvent())) { //点击菜单拉取消息时的事件

            } else if ("VIEW".equalsIgnoreCase(receiveXmlMsg.getEvent())) { //点击菜单跳转链接时的事件

            }
        }

        String answerMsg = buildAnswerMsg(receiveXmlMsg);

        result.setCode(true);
        result.setT(answerMsg);
        return result;
    }

    @Override
    public void OAuth2Base(String appId, String companyId, String code) {
        WXGetway wxGetway = wxGetwayMapper.findByAppIdAndCompanyId(appId, companyId);
        //code 换 access_token
        String url = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", wxGetway.getAppId(), wxGetway.getAppSecret(), code);
        String xml = HttpUtil.sslPost(url, "GET", null, null);

    }

    @Override
    public void OAuth2Userinfo(String appId, String companyId, String code) {
        WXGetway wxGetway = wxGetwayMapper.findByAppIdAndCompanyId(appId, companyId);
        //code 换 access_token
        String akUrl = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code", wxGetway.getAppId(), wxGetway.getAppSecret(), code);
        String akXml = HttpUtil.sslPost(akUrl, "GET", null, null);
        //{"access_token":"access_token","expires_in":7200,"refresh_token":"refresh_token","openid":"oFKMnwzhv83wK79L1rwfHbNniPkg","scope":"snsapi_userinfo"}

        //获取用户信息
        String access_token = "";
        String openid = "";
        String url = String.format("https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s&lang=zh_CN", access_token, openid);
        String infoXml = HttpUtil.sslPost(url, "GET", null, null);
        //{"openid":"oFKMnwzhv83wK79L1rwfHbNniPkg","nickname":"自然","sex":0,"language":"zh_CN","city":"昌平","province":"北京","country":"中国","headimgurl":"http:\/\/wx.qlogo.cn\/mmopen\/7cicwAfInRaibicbkoOOEWTIJtBVu14RbibQiaFMXLBxkBCLQP1ib6QY31Co3I2TGULr9Br0jgnnjdBW77xTKxVnZmibsNQLg2GyHiaP\/0","privilege":[]}

    }

    //保存订阅用户
    private String saveWXFans(ReceiveXmlMsg msg) {

        WXFans fans = wxFansMapper.findByGhidAndOpenId(msg.getToUserName(), msg.getFromUserName());
        Integer fansState = 0;
        if (null != fans) { //已存在, 更新
            fans.setEditTime(new Date());
            if ("subscribe".equalsIgnoreCase(msg.getEvent())) { //取消订阅事件
                fansState = 0;
            } else if ("unsubscribe".equalsIgnoreCase(msg.getEvent())) {
                fansState = 1;
            }
            wxFansMapper.updateStateById(msg.getFromUserName(), fansState);
            return msg.getFromUserName();
        }

        //不存在, 新增
        String id = new RandomGUID().toString();
        WXFans wxFans = new WXFans();
        wxFans.setCreateTime(new Date());
        wxFans.setEditTime(new Date());

        wxFans.setWxGhid(msg.getToUserName());
        wxFans.setOpenId(msg.getFromUserName());

        WXGetway wxGetway = wxGetwayMapper.findByGhid(msg.getToUserName());
        wxFans.setWxId(wxGetway.getWxId());

        if ("subscribe".equalsIgnoreCase(msg.getEvent())) { //取消订阅事件
            wxFans.setFansState(0);
        } else if ("unsubscribe".equalsIgnoreCase(msg.getEvent())) {
            wxFans.setFansState(1);
        }
        wxFansMapper.insertOne(wxFans);
        return id;
    }

    private String buildAnswerMsg(ReceiveXmlMsg receiveXmlMsg) {
        StringBuffer sb = new StringBuffer();

        //region //图文消息

        //        sb.append("<xml>");
//        sb.append(String.format("<ToUserName><![CDATA[%s]]></ToUserName>", msg.getFromUserName()));
//        sb.append(String.format("<FromUserName><![CDATA[%s]]></FromUserName>", msg.getToUserName()));
//        sb.append(String.format("<CreateTime>%s</CreateTime>", new Date().getTime()));
//        sb.append("<MsgType><![CDATA[news]]></MsgType>");
//        sb.append(String.format("<ArticleCount>%s</ArticleCount>", 2));
//        sb.append("<Articles>");
//        for (int i = 0; i < 2; i++) {
//            sb.append("<item>");
//            sb.append(String.format("<Title><![CDATA[%s]]></Title>", "title" + i));
//            sb.append(String.format("<Description><![CDATA[%s]]></Description>", "description" + i));
//            sb.append(String.format("<PicUrl><![CDATA[%s]]></PicUrl>", "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white_fe6da1ec.png"));
//            sb.append(String.format("<Url><![CDATA[%s]]></Url>", "https://www.baidu.com"));
//            sb.append("</item>");
//        }
//        sb.append("</Articles>");
//        sb.append("</xml>");
        //endregion

        //文本消息 超链接href要使用双引号, 换行使用\n
        sb = new StringBuffer();
        sb.append("<xml>");
        sb.append(String.format("<ToUserName><![CDATA[%s]]></ToUserName>", receiveXmlMsg.getFromUserName()));
        sb.append(String.format("<FromUserName><![CDATA[%s]]></FromUserName>", receiveXmlMsg.getToUserName()));
        sb.append(String.format("<CreateTime>%s</CreateTime>", new Date().getTime()));
        sb.append("<MsgType><![CDATA[text]]></MsgType>");
        //sb.append(String.format("<Content><![CDATA[%s]]></Content>", "<a href=\"http://www.baidu.com\">百度</a>\n<a href=\"http://www.qq.com\">腾讯</a>"));

        String content = "";

        //region 构建回复内容
        if ("text".equalsIgnoreCase(receiveXmlMsg.getMsgType())) { //文本, 需要回复
            if ("2005".equals(receiveXmlMsg.getContent())) {//用户当月GPRS查询
                content = "用户当月使用总GPRS: 1024 KB";
            } else if ("2011".equals(receiveXmlMsg.getContent())) {//用户余额信息实时查询
                content = "用户余额: 100.00 元";
            } else if ("2012".equals(receiveXmlMsg.getContent())) {//用户当月短信查询
                content = "当月短信使用: 100 条";
            } else if ("2020".equals(receiveXmlMsg.getContent())) {//套餐内GPRS流量使用情况实时查询(集团客户)
                content = "套餐ID: 910000000050475490";
                content += "\n产品实例ID: 54511113210";
                content += "\n套餐名称: GPRS中小流量新10元套餐";
                content += "\n套餐总量: 100 MB";
                content += "\n套餐使用: 10 MB";
                content += "\n套餐剩余: 90 MB";
            } else if ("2001".equals(receiveXmlMsg.getContent())) {//在线信息时时查询
                content = "GPRS在线状态: 在线";
                content += "\nIP地址: 127.0.0.1";
                content += "\n接入的APN: cmmtm";
                content += "\n接入方式: 4G";
            } else if ("2002".equals(receiveXmlMsg.getContent())) {//用户状态信息实时查询
                content = "状态: 正常";
            } else if ("2008".equals(receiveXmlMsg.getContent())) {//开关机信息实时查询
                content = "开关机状态: 开机";
            } else if ("2029".equals(receiveXmlMsg.getContent())) {//物联网卡多APN信息实时查询
                content = "终端IP: 127.0.0.1";
                content += "\n接入点: cmmtm";
                content += "\n接入方式: 4G";
                content += "\n在线状态: 在线";
                content += "\n状态变更时间: 2017-01-01 00:00:00";
            } else if ("4001".equals(receiveXmlMsg.getContent())) {//短信状态重置
                content = "重置结果: 成功";
            }
        }
        if (StringUtils.isBlank(content)) {
            content = "回复2005-用户当月GPRS查询\n回复2011-用户余额信息实时查询\n回复2012-用户当月短信查询\n回复2020-套餐内GPRS实时查询(集团客户)\n回复2001-在线信息实时查询\n回复2002-用户状态信息实时查询\n回复2008-开关机信息实时查询\n回复2029-物联卡多APN信息实时查询\n回复4001-短信状态重置";
        }
        //endregion

        content = "欢迎关注";
        sb.append(String.format("<Content><![CDATA[%s]]></Content>", content));

        sb.append("</xml>");
        return sb.toString();
    }

}

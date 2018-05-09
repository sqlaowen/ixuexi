package com.laowen.service;

import com.laowen.bean.WXGetway;
import com.laowen.utils.Result;

/**
 * Created by iyou on 2017/4/8.
 */

public interface WXGatewayService {

    /**
     * 获取临时access_token
     *
     * @param appId     公众号appID
     * @param companyId 系统分配的companyId
     * @return
     */
    Result<String> getAccessToken(String appId, String companyId);

    /**
     * @param appId     公众号appID
     * @param companyId 系统分配的companyId
     * @return
     */
    Result<WXGetway> findByAppIdAndCompanyId(String appId, String companyId);

    /**
     * 自动回复
     *
     * @param xml 微信服务器转发来的
     * @return
     */
    Result<String> autoAnswer(String xml);

    /**
     * 用户授权, 不弹出授权页面
     * 微信里访问路径,https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe21ad38684c7166d
     * &redirect_uri=http://zgxcw.ngrok.natapp.cn/wechat/account/bind?wxId=zgxcw_2016
     * &response_type=code&scope=snsapi_base&state=参数#wechat_redirect
     *
     * @param appId     公众号appID
     * @param companyId 系统分配的companyId
     * @param code      用户授权跳转code: HttpServletRequest.getParameter("code")
     */
    void OAuth2Base(String appId, String companyId, String code);

    /**
     * 用户授权, 弹出授权页面, 可不关注公众号
     * 微信里访问路径,https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxe21ad38684c7166d
     * &redirect_uri=http://zgxcw.ngrok.natapp.cn/wechat/account/bind?wxId=zgxcw_2016
     * &response_type=code&scope=snsapi_userinfo&state=参数#wechat_redirect
     *
     * @param appId     公众号appID
     * @param companyId 系统分配的companyId
     * @param code      用户授权跳转code: HttpServletRequest.getParameter("code")
     */
    void OAuth2Userinfo(String appId, String companyId, String code);

}

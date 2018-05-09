package com.laowen.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laowen.bean.domain.*;
import com.laowen.service.ChinaMobileService;
import com.laowen.utils.CommonUtil;
import com.laowen.utils.HttpUtil;
import com.laowen.utils.PropUtil;
import com.laowen.utils.Result;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by iyou on 2017/4/19.
 */
@Component
public class ChinaMobileServiceImpl implements ChinaMobileService {

    private Logger log = LoggerFactory.getLogger(ChinaMobileServiceImpl.class);

    private static final String CONTENTTYPE = "application/www-form-urlencoded";//;charset=utf-8

    //region 账务类

    @Override
    public Result<String> cmiot_api2005(ChinaMobileDto chinaMobileDto) {
        Result<String> result = new Result<String>();
        result.setCode(false);
        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/gprsusedinfosingle?ebid=0001000000012");
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(((JSONObject) (jsonObject.getJSONArray("result").get(0))).getString("total_gprs"));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<List<CMOIT2009Dto>> cmiot_api2009(ChinaMobileDto chinaMobileDto, String queryDate) {
        Result<List<CMOIT2009Dto>> result = new Result<>();
        result.setCode(false);

        Result<String> checkQueryDateResult = checkQueryDateInLast7Days(queryDate);
        if (!checkQueryDateResult.getCode()) {
            result.setMsg(checkQueryDateResult.getMsg());
            return result;
        }

        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/batchsmsusedbydate?ebid=0001000000026");
        url = url + "&query_date=" + queryDate;
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(JSON.parseArray(jsonObject.getString("result"), CMOIT2009Dto.class));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<List<CMOIT2010Dto>> cmiot_api2010(ChinaMobileDto chinaMobileDto, String queryDate) {
        Result<List<CMOIT2010Dto>> result = new Result<>();
        result.setCode(false);

        Result<String> checkQueryDateResult = checkQueryDateInLast7Days(queryDate);
        if (!checkQueryDateResult.getCode()) {
            result.setMsg(checkQueryDateResult.getMsg());
            return result;
        }

        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/batchgprsusedbydate?ebid=0001000000027");
        url = url + "&query_date=" + queryDate;
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(JSON.parseArray(jsonObject.getString("result"), CMOIT2010Dto.class));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<String> cmiot_api2011(ChinaMobileDto chinaMobileDto) {
        Result<String> result = new Result<String>();
        result.setCode(false);
        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/balancerealsingle?ebid=0001000000035");
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(((JSONObject) (jsonObject.getJSONArray("result").get(0))).getString("balance"));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<String> cmiot_api2012(ChinaMobileDto chinaMobileDto) {
        Result<String> result = new Result<String>();
        result.setCode(false);
        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/smsusedinfosingle?ebid=0001000000036");
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(((JSONObject) (jsonObject.getJSONArray("result").get(0))).getString("sms"));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<String> cmiot_api2013(String queryDate, String province) {
        Result<String> result = new Result<String>();
        result.setCode(false);
        String url = buildApiUrl("http://183.230.96.66:8087/v2/groupuserinfo?ebid=0001000000039", province);
        url = url + "&query_date=" + queryDate;
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(((JSONObject) (jsonObject.getJSONArray("result").get(0))).getString("total"));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<String> cmiot_api2014(ChinaMobileDto chinaMobileDto, String queryDate) {
        Result<String> result = new Result<String>();
        result.setCode(false);
        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/smsusedbydate?ebid=0001000000040");
        url = url + "&query_date=" + queryDate;
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(((JSONObject) (jsonObject.getJSONArray("result").get(0))).getString("sms"));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<CMOIT2020Dto> cmiot_api2020(ChinaMobileDto chinaMobileDto) {
        Result<CMOIT2020Dto> result = new Result<>();
        result.setCode(false);
        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/gprsrealtimeinfo?ebid=0001000000083");
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(JSON.parseArray(((JSONObject) jsonObject.getJSONArray("result").get(0)).getString("gprs"), CMOIT2020Dto.class).get(0));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    //endregion

    //region 通讯类

    @Override
    public Result<CMOIT2001Dto> cmiot_api2001(ChinaMobileDto chinaMobileDto) {
        Result<CMOIT2001Dto> result = new Result<>();
        result.setCode(false);
        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/gprsrealsingle?ebid=0001000000008");
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(JSON.parseArray(jsonObject.getString("result"), CMOIT2001Dto.class).get(0));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<String> cmiot_api2002(ChinaMobileDto chinaMobileDto) {
        Result<String> result = new Result<String>();
        result.setCode(false);
        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/userstatusrealsingle?ebid=0001000000009");
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            String status = ((JSONObject) (jsonObject.getJSONArray("result").get(0))).getString("STATUS");
            switch (status) {
                case "00":
                    result.setT("正常");
                    break;
                case "01":
//                    result.setT("单向停机");
//                    break;
                case "02":
//                    result.setT("停机");
//                    break;
                case "03":
//                    result.setT("预销号");
//                    break;
                case "04":
//                    result.setT("销号");
//                    break;
                case "05":
//                    result.setT("过户");
//                    break;
                case "06":
//                    result.setT("休眠");
//                    break;
                    result.setT("停机");
                    break;
                case "07":
                    result.setT("待激");
                    break;
                case "99":
                    result.setT("号码不存在");
                    break;

            }
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<CMOIT2003Dto> cmiot_api2003(ChinaMobileDto chinaMobileDto) {
        Result<CMOIT2003Dto> result = new Result<CMOIT2003Dto>();
        result.setCode(false);
        String url = buildApiUrl("http://183.230.96.66:8087/v2/cardinfo?ebid=0001000000010", chinaMobileDto.getProvince());
        if (StringUtils.isNotBlank(chinaMobileDto.getMsisdn())) {
            url += "&card_info=" + chinaMobileDto.getMsisdn() + "&type=0";
        } else if (StringUtils.isNotBlank(chinaMobileDto.getImsi())) {
            url += "&card_info=" + chinaMobileDto.getImsi() + "&type=1";
        } else if (StringUtils.isNotBlank(chinaMobileDto.getIccid())) {
            url += "&card_info=" + chinaMobileDto.getIccid() + "&type=2";
        }
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT(JSON.parseArray(jsonObject.getString("result"), CMOIT2003Dto.class).get(0));
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<String> cmiot_api2008(ChinaMobileDto chinaMobileDto) {
        Result<String> result = new Result<String>();
        result.setCode(false);
        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/onandoffrealsingle?ebid=0001000000025");
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            String status = ((JSONObject) (jsonObject.getJSONArray("result").get(0))).getString("status");
            switch (status) {
                case "0":
                    result.setT("关机");
                    break;
                case "1":
                    result.setT("开机");
                    break;
            }
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    @Override
    public Result<CMOIT2029Dto> cmiot_api2029(ChinaMobileDto chinaMobileDto) {
        return null;
    }

    //endregion

    //region 控制类

    @Override
    public Result<String> cmiot_api4001(ChinaMobileDto chinaMobileDto) {
        Result<String> result = new Result<String>();
        result.setCode(false);
        String url = buildApiUrl(chinaMobileDto, "http://183.230.96.66:8087/v2/smsstatusreset?ebid=0001000000034");
        String json = HttpUtil.httpGet(url, new HashMap<String, String>() {{
            put("Content-Type", CONTENTTYPE);
        }});
        JSONObject jsonObject = JSON.parseObject(json);
        if ("0".equals(jsonObject.getString("status"))) {
            result.setCode(true);
            result.setT("重置成功");
        } else {
            result.setMsg(jsonObject.getString("message"));
        }
        return result;
    }

    //endregion


    //region 私有方法


    /**
     * 构建url
     *
     * @param preUrl 带有ebid的url前缀
     * @return
     */
    private String buildApiUrl(String preUrl, String province) {
        String appIdFmt = String.format("chinaAppId-%s", province);
        String appPwdFmt = String.format("chinaAppPwd-%s", province);
        String appid = PropUtil.getProp(appIdFmt);
        String pwd = PropUtil.getProp(appPwdFmt);
        String transid = appid + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + CommonUtil.getLenNumberStr(8);
        MessageDigest sha256Digest = DigestUtils.getSha256Digest();
        byte[] digest = sha256Digest.digest((appid + pwd + transid).getBytes());
        String token = Hex.encodeHexString(digest);
        String url = preUrl + "&appid=%s&transid=%s&token=%s";
        url = String.format(url, appid, transid, token);
        return url;
    }

    /**
     * 构建url
     *
     * @param chinaMobileDto
     * @param preUrl         带有ebid的url前缀
     * @return
     */
    private String buildApiUrl(ChinaMobileDto chinaMobileDto, String preUrl) {
        String url = buildApiUrl(preUrl, chinaMobileDto.getProvince());
        if (StringUtils.isNotBlank(chinaMobileDto.getMsisdn())) {
            url = url + "&msisdn=" + chinaMobileDto.getMsisdn();
        } else if (StringUtils.isNotBlank(chinaMobileDto.getIccid())) {
            url = url + "&iccid=" + chinaMobileDto.getIccid();
        } else if (StringUtils.isNotBlank(chinaMobileDto.getImsi())) {
            url = url + "&imsi=" + chinaMobileDto.getImsi();
        } else {
            log.error("msisdn/iccid/imsi至少提供一个, {}", chinaMobileDto);
            throw new RuntimeException(String.format("msisdn/iccid/imsi至少提供一个, %s", chinaMobileDto));
        }
        return url;
    }

    /**
     * 校验参数queryDate是否在最近7日内
     *
     * @param queryDate
     * @return
     */
    private Result<String> checkQueryDateInLast7Days(String queryDate) {
        Result<String> result = new Result<String>() {{
            setCode(false);
        }};
        Date date = null;
        try {
            date = DateUtils.parseDate(queryDate, "yyyyMMdd");
        } catch (ParseException e) {
            log.error("日期格式化异常", e);
            result.setMsg("日期格式不正确,正确格式yyyyMMdd; queryDate:" + queryDate);
            return result;
        }
        if (CommonUtil.isInLast7Days(date, false)) {
            result.setMsg("仅支持查询最近7天中某一天的数据, queryDate:" + queryDate);
            return result;
        }
        result.setCode(true);
        return result;
    }

    //endregion
}

package com.laowen.service;

import com.laowen.bean.domain.*;
import com.laowen.utils.Result;

import java.util.List;

/**
 * Created by iyou on 2017/4/19.
 */
public interface ChinaMobileService {

    // region 账务类

    /**
     * CMIOT_API2005 用户当月GPRS查询
     *
     * @param chinaMobileDto
     * @return
     */
    Result<String> cmiot_api2005(ChinaMobileDto chinaMobileDto);

    /**
     * CMIOT_API2009 短信批量查询
     * 仅支持查询最近7天中某一的数据
     *
     * @param chinaMobileDto
     * @param queryDate      查询时间, 如系统当前时间 20150421, 7日内(20150414-20150420)某一天有效
     * @return
     */
    Result<List<CMOIT2009Dto>> cmiot_api2009(ChinaMobileDto chinaMobileDto, String queryDate);

    /**
     * cmiot_api2010 流量信息批量查询
     * 仅支持查询最近7天中某一的数据
     *
     * @param chinaMobileDto
     * @param queryDate      查询时间, 如系统当前时间 20150421, 7日内(20150414-20150420)某一天有效
     * @return
     */
    Result<List<CMOIT2010Dto>> cmiot_api2010(ChinaMobileDto chinaMobileDto, String queryDate);

    /**
     * CMIOT_API2011 用户余额信息实时查询
     *
     * @param chinaMobileDto
     * @return
     */
    Result<String> cmiot_api2011(ChinaMobileDto chinaMobileDto);

    /**
     * CMIOT_API2012 用户当月短信查询
     *
     * @param chinaMobileDto
     * @return
     */
    Result<String> cmiot_api2012(ChinaMobileDto chinaMobileDto);

    /**
     * CMIOT_API2013 集团用户数查询
     *
     * @param queryDate 查询日期(某一天, 最晚为当前时间)
     * @param province  省份
     * @return
     */
    Result<String> cmiot_api2013(String queryDate, String province);

    /**
     * CMIOT_API2014 用户某天短信使用查询
     *
     * @param chinaMobileDto
     * @param queryDate      查询日期(某一天, 最晚当前时间-1天)
     * @return
     */
    Result<String> cmiot_api2014(ChinaMobileDto chinaMobileDto, String queryDate);

    /**
     * CMIOT_API2020 套餐内GPRS流量使用情况实时查询(集团客户)
     *
     * @param chinaMobileDto
     * @return
     */
    Result<CMOIT2020Dto> cmiot_api2020(ChinaMobileDto chinaMobileDto);

    // endregion

    //region 通讯类

    /**
     * CMOIT_API2001 在线信息时时查询
     *
     * @param chinaMobileDto
     */
    Result<CMOIT2001Dto> cmiot_api2001(ChinaMobileDto chinaMobileDto);

    /**
     * CMIOT_API2002 用户状态信息实时查询
     *
     * @param chinaMobileDto
     * @return
     */
    Result<String> cmiot_api2002(ChinaMobileDto chinaMobileDto);

    /**
     * CMOIT_API2003 号码信息查询
     *
     * @param chinaMobileDto
     * @return
     */
    Result<CMOIT2003Dto> cmiot_api2003(ChinaMobileDto chinaMobileDto);

    /**
     * CMIOT_API2008 开关机信息实时查询
     *
     * @param chinaMobileDto
     * @return
     */
    Result<String> cmiot_api2008(ChinaMobileDto chinaMobileDto);

    /**
     * CMIOT_API2029 物联网卡多APN信息实时查询
     * 缺少ebid, 暂时做不了
     *
     * @param chinaMobileDto
     * @return
     */
    Result<CMOIT2029Dto> cmiot_api2029(ChinaMobileDto chinaMobileDto);

    //endregion

    //region 控制类

    /**
     * CMIOT_API4001 短信状态重置
     *
     * @param chinaMobileDto
     * @return
     */
    Result<String> cmiot_api4001(ChinaMobileDto chinaMobileDto);

    //endregion


}

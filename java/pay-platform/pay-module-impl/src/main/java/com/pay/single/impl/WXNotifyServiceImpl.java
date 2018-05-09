package com.pay.single.impl;

import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.pay.single.bean.WXCoupon;
import com.pay.single.bean.WXNotify;
import com.pay.single.dao.WXCouponMapper;
import com.pay.single.dao.WXNotifyMapper;

import api.pay.single.dto.req.CreateWXCouponReqDto;
import api.pay.single.dto.req.CreateWXNotifyReqDto;
import api.pay.single.service.WXNotifyService;
import api.pay.utils.ServiceException;

public class WXNotifyServiceImpl implements WXNotifyService {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  
  private WXNotifyMapper wxNotifyMapper;
  public void setWxNotifyMapper(WXNotifyMapper wxNotifyMapper) {
    this.wxNotifyMapper = wxNotifyMapper;
  }
  
  private WXCouponMapper wxCouponMapper;
  public void setWxCouponMapper(WXCouponMapper wxCouponMapper) {
    this.wxCouponMapper = wxCouponMapper;
  }
  
  @Override
  public String saveOne(CreateWXNotifyReqDto dto) throws ServiceException {
    if (null == dto) {
      log.error("请求参数CreateWXNotifyReqDto为空...");
      throw new ServiceException("请求参数CreateWXNotifyReqDto为空...");
    }
    WXNotify record = new WXNotify();
    BeanUtils.copyProperties(dto, record);
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    record.setWxNotifyId(id);
    record.setCreateTime(new Date());
    wxNotifyMapper.saveOne(record);

    if (null != dto.getWxCouponList()) {
      // ---------------微信优惠券
      WXCoupon wxCoupon = null;
      for (CreateWXCouponReqDto couponDto : dto.getWxCouponList()) {
        wxCoupon = new WXCoupon();
        BeanUtils.copyProperties(couponDto, wxCoupon);
        wxCoupon.setYhId(UUID.randomUUID().toString().replaceAll("-", ""));
        wxCoupon.setWxNotifyId(id);
        wxCouponMapper.saveOne(wxCoupon);
      }
      // ---------------微信优惠券
    }
    
    return id;
  }

}

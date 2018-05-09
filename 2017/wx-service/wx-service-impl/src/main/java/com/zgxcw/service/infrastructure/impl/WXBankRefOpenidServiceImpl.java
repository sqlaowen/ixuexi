package com.zgxcw.service.infrastructure.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.bean.WXBankRefOpenid;
import com.zgxcw.service.infrastructure.bean.WXBankUNRefOpenidLog;
import com.zgxcw.service.infrastructure.dao.WXBankRefOpenidMapper;
import com.zgxcw.service.infrastructure.dao.WXBankUNRefOpenidLogMapper;
import com.zgxcw.service.infrastructure.dto.request.wxbankrefopenid.CreateWXBankRefOpenidRequest;
import com.zgxcw.service.infrastructure.dto.response.WXBankRefOpenidResponse;
import com.zgxcw.service.infrastructure.service.WXBankRefOpenidService;

public class WXBankRefOpenidServiceImpl implements WXBankRefOpenidService {
  private Logger log = LoggerFactory.getLogger(this.getClass());

  private WXBankRefOpenidMapper wxBankRefOpenidMapper;
  public void setWxBankRefOpenidMapper(WXBankRefOpenidMapper wxBankRefOpenidMapper) {
    this.wxBankRefOpenidMapper = wxBankRefOpenidMapper;
  }
  
  private WXBankUNRefOpenidLogMapper wxBankUNRefOpenidLogMapper;
  public void setWxBankUNRefOpenidLogMapper(WXBankUNRefOpenidLogMapper wxBankUNRefOpenidLogMapper) {
    this.wxBankUNRefOpenidLogMapper = wxBankUNRefOpenidLogMapper;
  }

  @Override
  public String saveOne(CreateWXBankRefOpenidRequest createWXBankRefOpenidRequest) throws ServiceException {
    if (null == createWXBankRefOpenidRequest) {
      log.error("请求参数createWXBankRefOpenidRequest为空");
      throw new ServiceException("请求参数createWXBankRefOpenidRequest为空");
    }
    if (StringUtils.isBlank(createWXBankRefOpenidRequest.getWxId())) {
      log.error("请求参数createWXBankRefOpenidRequest.getWxId为空");
      throw new ServiceException("请求参数createWXBankRefOpenidRequest.getWxId为空");
    }
    if (StringUtils.isBlank(createWXBankRefOpenidRequest.getBankName())) {
      log.error("请求参数createWXBankRefOpenidRequest.getBankName");
      throw new ServiceException("请求参数createWXBankRefOpenidRequest.getBankName");
    }
    if (StringUtils.isBlank(createWXBankRefOpenidRequest.getBankNo())) {
      log.error("请求参数createWXBankRefOpenidRequest.getBankNo为空");
      throw new ServiceException("请求参数createWXBankRefOpenidRequest.getBankNo为空");
    }
    if (StringUtils.isBlank(createWXBankRefOpenidRequest.getOpenid())) {
      log.error("请求参数createWXBankRefOpenidRequest.getOpenid为空");
      throw new ServiceException("请求参数createWXBankRefOpenidRequest.getOpenid为空");
    }
    WXBankRefOpenid ref=  wxBankRefOpenidMapper.findByOpenid(createWXBankRefOpenidRequest.getOpenid(), createWXBankRefOpenidRequest.getWxId());
    if (null != ref) {
      return ref.getRefId();
    }
    WXBankRefOpenid model = new WXBankRefOpenid();
    BeanUtils.copyProperties(createWXBankRefOpenidRequest, model);
    String id = UUID.randomUUID().toString().replace("-", "");
    model.setRefId(id);
    model.setCreateTime(System.currentTimeMillis());
    wxBankRefOpenidMapper.saveOne(model);
    return id;
  }

  @Override
  public WXBankRefOpenidResponse findById(String refId) throws ServiceException {
    if (StringUtils.isBlank(refId)) {
      log.error("请求参数refId为空");
      throw new ServiceException("请求参数refId为空");
    }
    return buildRes(wxBankRefOpenidMapper.findById(refId));
  }

  @Override
  public Collection<WXBankRefOpenidResponse> findByBankNo(String bankNo, String wxId) throws ServiceException {
    if (StringUtils.isBlank(bankNo)) {
      log.error("请求参数bankNo为空");
      throw new ServiceException("请求参数bankNo为空");
    }
    if (StringUtils.isBlank(wxId)) {
      log.error("请求参数wxId为空");
      throw new ServiceException("请求参数wxId为空");
    }
    return buildResList(wxBankRefOpenidMapper.findByBankNo(bankNo,wxId));
  }

  @Override
  public WXBankRefOpenidResponse findByOpenid(String openid, String wxId) throws ServiceException {
    if (StringUtils.isBlank(openid)) {
      log.error("请求参数openid为空");
      throw new ServiceException("请求参数openid为空");
    }
    if (StringUtils.isBlank(wxId)) {
      log.error("请求参数wxId为空");
      throw new ServiceException("请求参数wxId为空");
    }
    return buildRes(wxBankRefOpenidMapper.findByOpenid(openid,wxId));
  }

  @Override
  public int editBankNo(String bankName,String bankNo, String openid, String wxId) throws ServiceException {
    if (StringUtils.isBlank(openid)) {
      log.error("请求参数openid为空");
      throw new ServiceException("请求参数openid为空");
    }
    if (StringUtils.isBlank(wxId)) {
      log.error("请求参数wxId为空");
      throw new ServiceException("请求参数wxId为空");
    }
    // 解绑银行卡, 记录绑定过的日志
    if (StringUtils.isBlank(bankNo)) {
      WXBankRefOpenid ref = wxBankRefOpenidMapper.findByOpenid(openid, wxId);
      if (null != ref) {
        WXBankUNRefOpenidLog record = new WXBankUNRefOpenidLog();
        String logId = UUID.randomUUID().toString().replace("-", "");
        record.setLogId(logId);
        record.setCreateTime(System.currentTimeMillis());
        record.setBankName(ref.getBankName());
        record.setBankNo(ref.getBankNo());
        record.setRefId(ref.getRefId());
        wxBankUNRefOpenidLogMapper.saveOne(record);
      }
    }
    return wxBankRefOpenidMapper.editBankNo(bankName,bankNo, openid, wxId);
  }

  private WXBankRefOpenidResponse buildRes(WXBankRefOpenid record) {
    if (null == record) {
      return null;
    }
    WXBankRefOpenidResponse res = new WXBankRefOpenidResponse();
    BeanUtils.copyProperties(record, res);
    return res;
  }
  
  private Collection<WXBankRefOpenidResponse> buildResList(List<WXBankRefOpenid> recordList) {
    if (null == recordList || 0 == recordList.size()) {
      return null;
    }
    Collection<WXBankRefOpenidResponse> resList = new ArrayList<>();
    WXBankRefOpenidResponse res = null;
    for (WXBankRefOpenid record : recordList) {
      res = buildRes(record);
      resList.add(res);
    }
    return resList;
  }

}

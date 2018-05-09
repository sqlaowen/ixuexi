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
import com.zgxcw.service.infrastructure.bean.WXUserRefOpenid;
import com.zgxcw.service.infrastructure.dao.WXUserRefOpenidMapper;
import com.zgxcw.service.infrastructure.dto.request.wxuserrefopenid.CreateWXUserRefOpenidRequest;
import com.zgxcw.service.infrastructure.dto.response.WXUserRefOpenidResponse;
import com.zgxcw.service.infrastructure.service.WXUserRefOpenidService;

public class WXUserRefOpenidServiceImpl implements WXUserRefOpenidService {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  
  private WXUserRefOpenidMapper wxUserRefOpenidMapper;
  public void setWxUserRefOpenidMapper(WXUserRefOpenidMapper wxUserRefOpenidMapper) {
    this.wxUserRefOpenidMapper = wxUserRefOpenidMapper;
  }

  @Override
  public String saveOne(CreateWXUserRefOpenidRequest createWXUserRefOpenidRequest) throws ServiceException {
    if(createWXUserRefOpenidRequest == null){
      log.error("请求参数createWXUserRefOpenidRequest为空");
      throw new ServiceException("请求参数createWXUserRefOpenidRequest为空");
    }
    if(StringUtils.isBlank(createWXUserRefOpenidRequest.getWxId())){
      log.error("请求参数createWXUserRefOpenidRequest.getWxId为空");
      throw new ServiceException("请求参数createWXUserRefOpenidRequest.getWxId为空");
    }
    if(StringUtils.isBlank(createWXUserRefOpenidRequest.getQiyeId()) && StringUtils.isBlank(createWXUserRefOpenidRequest.getYhuId())){
      log.error("请求参数createWXUserRefOpenidRequest.getQiyeId和getYhuId不能同时为空");
      throw new ServiceException("请求参数createWXUserRefOpenidRequest.getQiyeId和getYhuId不能同时为空");
    }
    if(StringUtils.isBlank(createWXUserRefOpenidRequest.getOpenid())){
      log.error("请求参数createWXUserRefOpenidRequest.getOpenid为空");
      throw new ServiceException("请求参数createWXUserRefOpenidRequest.getOpenid为空");
    }
    WXUserRefOpenid wxRef = wxUserRefOpenidMapper.findByQiyeidOpenid(
        createWXUserRefOpenidRequest.getWxId(), createWXUserRefOpenidRequest.getOpenid(),
        createWXUserRefOpenidRequest.getQiyeId(), createWXUserRefOpenidRequest.getYhuId());
    if (null != wxRef) {
      return wxRef.getRefId();
    }
    WXUserRefOpenid model = new WXUserRefOpenid();
    BeanUtils.copyProperties(createWXUserRefOpenidRequest, model);
    String id = UUID.randomUUID().toString().replace("-", "");
    model.setRefId(id);
    model.setCreateTime(System.currentTimeMillis());
    wxUserRefOpenidMapper.saveOne(model);
    return id;
  }

  @Override
  public WXUserRefOpenidResponse findById(String refId) throws ServiceException {
    if(refId == null){
      log.error("请求参数refId为空");
      throw new ServiceException("请求参数refId为空");
    }
    return buildRes(wxUserRefOpenidMapper.findById(refId));
  }

  @Override
  public Collection<WXUserRefOpenidResponse> findByAccountId(String wxId, String qiyeId) throws ServiceException {
    if (StringUtils.isBlank(wxId)) {
      log.error("请求参数wxId为空");
      throw new ServiceException("请求参数wxId为空");
    }
    if (StringUtils.isBlank(qiyeId)) {
      log.error("请求参数qiyeId不能同时为空");
      throw new ServiceException("请求参数qiyeId不能同时为空");
    }
    return buildResList(wxUserRefOpenidMapper.findByAccountId(wxId, qiyeId));
  }

  private WXUserRefOpenidResponse buildRes(WXUserRefOpenid record) {
    if (null == record) {
      return null;
    }
    WXUserRefOpenidResponse res = new WXUserRefOpenidResponse();
    BeanUtils.copyProperties(record, res);
    return res;
  }
  
  private Collection<WXUserRefOpenidResponse> buildResList(List<WXUserRefOpenid> recordList) {
    if (null == recordList || 0 == recordList.size()) {
      return null;
    }
    Collection<WXUserRefOpenidResponse> resList = new ArrayList<>();
    WXUserRefOpenidResponse res = null;
    for (WXUserRefOpenid record : recordList) {
      res = buildRes(record);
      resList.add(res);
    }
    return resList;
  }

}

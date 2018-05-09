package com.zgxcw.service.infrastructure.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zgxcw.service.infrastructure.dto.request.page.PageRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.QueryWxAutoResRequest;
import com.zgxcw.service.infrastructure.dto.response.PageResponse;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.bean.WXAutoRes;
import com.zgxcw.service.infrastructure.dao.WXAutoResMapper;
import com.zgxcw.service.infrastructure.dto.request.wxautores.CreateWXAutoResRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautores.UpdateWXAutoResRequest;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResResponse;
import com.zgxcw.service.infrastructure.service.WXAutoResService;

public class WXAutoResServiceImpl implements WXAutoResService {

  private Logger log = LoggerFactory.getLogger(this.getClass());
  private WXAutoResMapper wxAutoResMapper;

  public void setWxAutoResMapper(WXAutoResMapper wxAutoResMapper) {
    this.wxAutoResMapper = wxAutoResMapper;
  }

  /**
   * 根据id查询
   * 
   * @param resId :消息id
   * @return WXAutoResResponse
   * @throws ServiceException
   */
  @Override
  public WXAutoResResponse findById(String resId) throws ServiceException {
    if (null == resId || "".equals(resId.trim())) {
      log.error("请求参数resId为空...");
      throw new ServiceException("请求参数resId为空...");
    }
    return buildRes(wxAutoResMapper.findById(resId));
  }
  
  /**
   * 查询要使用的回复消息
   * 
   * @param msgType: 消息类型
   * @param wxId :微信网关id
   * @return
   * @throws ServiceException
   */
  @Override
  public WXAutoResResponse findTheSet(Integer msgType,String wxId) throws ServiceException {
    if (null == msgType) {
      log.error("请求参数msgType为空...");
      throw new ServiceException("请求参数msgType为空...");
    }
    if (null == wxId || "".equals(wxId.trim())) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    return buildRes(wxAutoResMapper.findTheSet(msgType, wxId));
  }
    
  /**
   * 根据回复/消息类型查询
   * 
   * @param resType :回复类型(可空)
   * @param msgType :消息类型(可空)
   * @param wxId :微信网关id(不可空)
   * @return
   * @throws ServiceException
   */
  @Override
  public List<WXAutoResResponse> findByResOrMsgType(String resType, String msgType, String wxId) throws ServiceException {
    
    if (null == wxId || "".equals(wxId.trim())) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    return buildResList(wxAutoResMapper.findByResOrMsgType(resType, msgType, wxId));
  }

  /**
   * 保存
   * 
   * @param request :CreateWXAutoResRequest
   * @return
   * @throws ServiceException
   */
  @Override
  public String saveOne(CreateWXAutoResRequest request) throws ServiceException {
    if (null == request) {
      log.error("请求参数CreateWXAutoResRequest为空...");
      throw new ServiceException("请求参数CreateWXAutoResRequest为空...");
    }
    if (null == request.getWxId() || "".equals(request.getWxId().trim())) {
      log.error("请求参数CreateWXAutoResRequest.WxId为空...");
      throw new ServiceException("请求参数CreateWXAutoResRequest.WxId为空...");
    }
    WXAutoRes model = new WXAutoRes();
    BeanUtils.copyProperties(request, model);
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    model.setResId(id);
    model.setCreateTime(new Date().getTime());
    wxAutoResMapper.saveOne(model);
    return id;
  }
  
  /**
   * 修改
   * 
   * @param request :UpdateWXAutoResRequest
   * @return 受影响行数
   * @throws ServiceException
   */
  @Override
  public int editById(UpdateWXAutoResRequest request) throws ServiceException {
    if (null == request) {
      log.error("请求参数UpdateWXAutoResRequest为空...");
      throw new ServiceException("请求参数UpdateWXAutoResRequest为空...");
    }
    if (null == request.getResId() || "".equals(request.getResId().trim())) {
      log.error("请求参数UpdateWXAutoResRequest.resId为空...");
      throw new ServiceException("请求参数UpdateWXAutoResRequest.resId为空...");
    }
    if (null == request.getWxId() || "".equals(request.getWxId().trim())) {
      log.error("请求参数UpdateWXAutoResRequest.WxId为空...");
      throw new ServiceException("请求参数UpdateWXAutoResRequest.WxId为空...");
    }
    WXAutoRes model = new WXAutoRes();
    BeanUtils.copyProperties(request, model);
    model.setUpdateTime(new Date().getTime());
    int rev = 0;
    rev = wxAutoResMapper.editById(model);
    return rev;
  }

  /**
   * 根据Id删除
   * @param resId
   * @return
   * @throws ServiceException
   */
  public int deleteById(String resId) throws  ServiceException{
    if (null == resId|| "".equals(resId.trim())) {
      log.error("请求参数resId为空...");
      throw new ServiceException("请求参数resId为空...");
    }
    int rev = 0;
    rev = wxAutoResMapper.deleteById(resId);
    return rev;
  }

  /**
   * 将该条设置为启用回复,其它设置为不启用回复
   * 
   * @param resId
   * @param msgType
   * @param wxId
   * @throws ServiceException
   */
  @Override
  public void editSetEnable(String resId, String msgType, String wxId) throws ServiceException {
    if (StringUtils.isBlank(resId)) {
      log.error("请求参数resId为空...");
      throw new ServiceException("请求参数resId为空...");
    }
    if (StringUtils.isBlank(msgType)) {
      log.error("请求参数msgType为空...");
      throw new ServiceException("请求参数msgType为空...");
    }
    if (null == wxId || "".equals(wxId.trim())) {
      log.error("请求参数wxId为空...");
      throw new ServiceException("请求参数wxId为空...");
    }
    WXAutoRes model = new WXAutoRes();
    model.setResId(resId);
    model.setResState(0);
    wxAutoResMapper.editById(model);
    wxAutoResMapper.editOthersDisable(resId, msgType, wxId);
  }
  
  /**
   * 分页查询
   * @param request
   * @return
   * @throws ServiceException
   */
  @Override
  public PageResponse<WXAutoResResponse> getPageList(PageRequest<QueryWxAutoResRequest> request) throws ServiceException{
    if(request==null){
      log.error("请求参数request为null...");
      throw new ServiceException("请求参数request为null...");
    }
    if(request.getPageNum()<=0){
      log.error("请求参数PageNum必须大于0...");
      throw new ServiceException("请求参数PageNum必须大于0...");
    }
    if(request.getPageSize()<=0){
      log.error("请求参数PageSize必须大于0...");
      throw new ServiceException("请求参数PageSize必须大于0...");
    }
    PageResponse<WXAutoResResponse> pageResponse = new PageResponse<WXAutoResResponse>();
    PageHelper.startPage(request.getPageNum(), request.getPageSize());
    List<WXAutoRes> modelList=  wxAutoResMapper.getPageList(request.getT().getWxId(),
        request.getT().getResName(), request.getT().getResType(), request.getT().getResState(),request.getT().getMsgType());
    if(modelList!=null&&modelList.size()>0){
      pageResponse.setList(buildResList(modelList));
      PageInfo<WXAutoRes> pageInfo = new PageInfo<WXAutoRes>(modelList);
      pageResponse.setTotal(pageInfo.getTotal());
    }
    return pageResponse;
  }
  
  /**
   * 构建WXAutoResResponse
   * 
   * @param model :WXAutoRes
   * @return
   */
  private WXAutoResResponse buildRes(WXAutoRes model) {
    WXAutoResResponse res = null;
    if (null != model) {
      res = new WXAutoResResponse();
      BeanUtils.copyProperties(model, res);
    }
    return res;
  }

  //
  private List<WXAutoResResponse> buildResList(List<WXAutoRes> modelList) {
    List<WXAutoResResponse> resList = null;
    if (null != modelList && modelList.size() > 0) {
      resList = new ArrayList<WXAutoResResponse>();
      WXAutoResResponse res = null;
      for (WXAutoRes model : modelList) {
        res = buildRes(model);
        if (null != res) {
          resList.add(res);
        }
      }
    }
    return resList;
  }

}

package com.zgxcw.service.infrastructure.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.zgxcw.framework.service.ServiceException;
import com.zgxcw.service.infrastructure.bean.WXAutoResContent;
import com.zgxcw.service.infrastructure.dao.WXAutoResContentMapper;
import com.zgxcw.service.infrastructure.dto.request.wxautorescontent.CreateWXAutoResContentRequest;
import com.zgxcw.service.infrastructure.dto.request.wxautorescontent.UpdateWXAutoResContentRequest;
import com.zgxcw.service.infrastructure.dto.response.WXAutoResContentResponse;
import com.zgxcw.service.infrastructure.service.WXAutoResContentService;

public class WXAutoResContentServiceImpl implements WXAutoResContentService {

  private Logger log=LoggerFactory.getLogger(this.getClass());
  
  private WXAutoResContentMapper wxAutoResContentMapper;  
  public void setWxAutoResContentMapper(WXAutoResContentMapper wxAutoResContentMapper) {
    this.wxAutoResContentMapper = wxAutoResContentMapper;
  }

  /**
   * 保存
   * 
   * @param request :CreateWXAutoResContentRequest
   * @return
   * @throws ServiceException
   */
  @Override
  public String saveOne(CreateWXAutoResContentRequest request) throws ServiceException {
    if(null==request){
      log.error("请求参数CreateWXAutoResContentRequest为空...");
      throw new ServiceException("请求参数CreateWXAutoResContentRequest为空...");
    }
    if(StringUtils.isBlank(request.getResId())){
      log.error("请求参数CreateWXAutoResContentRequest.resId为空...");
      throw new ServiceException("请求参数CreateWXAutoResContentRequest.resId为空...");
    }
    WXAutoResContent model=new WXAutoResContent();
    BeanUtils.copyProperties(request, model);
    String id = UUID.randomUUID().toString().replaceAll("-", "");
    model.setContentId(id);
    model.setCreateTime(new Date().getTime());
    wxAutoResContentMapper.saveOne(model);
    return id;
  }

  /**
   * 根据id查询
   * 
   * @param contentId
   * @return
   * @throws ServiceException
   */
  @Override
  public WXAutoResContentResponse findById(String contentId) throws ServiceException {
    if(StringUtils.isBlank(contentId)){
      log.error("请求参数contentId为空...");
      throw new ServiceException("请求参数contentId为空...");
    }
    return buildRes(wxAutoResContentMapper.findById(contentId));
  }

  /**
   * 根据自动回复id查询
   * 
   * @param resId
   * @return
   * @throws ServiceException
   */
  @Override
  public List<WXAutoResContentResponse> findByResId(String resId) throws ServiceException {
    if(StringUtils.isBlank(resId)){
      log.error("请求参数resId为空...");
      throw new ServiceException("请求参数resId为空...");
    }
    return buildResList(wxAutoResContentMapper.findByResId(resId));
  }

  /**
   * 修改
   * 
   * @param request :UpdateWXAutoResContentRequest
   * @return
   * @throws ServiceException
   */
  @Override
  public int eidtById(UpdateWXAutoResContentRequest request) throws ServiceException {
    if(null==request){
      log.error("请求参数UpdateWXAutoResContentRequest为空...");
      throw new ServiceException("请求参数UpdateWXAutoResContentRequest为空...");
    }
    if(StringUtils.isBlank(request.getContentId())){
      log.error("请求参数UpdateWXAutoResContentRequest.contentId为空...");
      throw new ServiceException("请求参数UpdateWXAutoResContentRequest.contentId为空...");
    }
    WXAutoResContent model=new WXAutoResContent();
    BeanUtils.copyProperties(request, model);
    model.setUpdateTime(new Date().getTime());
    return wxAutoResContentMapper.eidtById(model);
  }

  /**
   * 根据resId删除
   * @param resId
   * @return
   * @throws ServiceException
   */
  public int deleteByResId(String resId) throws  ServiceException{
    if(StringUtils.isBlank(resId)){
      log.error("请求参数resId为空...");
      throw new ServiceException("请求参数resId为空...");
    }
    return wxAutoResContentMapper.deleteByResId(resId);
  }

  /**
   * 构建WXAutoResContentResponse
   * @param model :WXAutoResContent
   * @return
   */
  private WXAutoResContentResponse buildRes(WXAutoResContent model) {
    WXAutoResContentResponse res = null;
    if (null != model) {
      res = new WXAutoResContentResponse();
      BeanUtils.copyProperties(model, res);
    }
    return res;
  }

  /**
   * 构建List<WXAutoResContentResponse>
   * @param modelList :List<WXAutoResContent>
   * @return List<WXAutoResContentResponse>
   */
  private List<WXAutoResContentResponse> buildResList(List<WXAutoResContent> modelList) {
    List<WXAutoResContentResponse> resList = null;
    if (null != modelList) {
      resList = new ArrayList<WXAutoResContentResponse>();
      WXAutoResContentResponse res = null;
      for (WXAutoResContent model : modelList) {
        res = buildRes(model);
        if (null != res) {
          resList.add(res);
        }
      }
    }
    return resList;
  }

}

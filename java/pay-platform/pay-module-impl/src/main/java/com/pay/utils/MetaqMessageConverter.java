package com.pay.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSON;
import com.taobao.metamorphosis.client.extension.spring.MessageBodyConverter;
import com.taobao.metamorphosis.exception.MetaClientException;

public class MetaqMessageConverter implements MessageBodyConverter<Serializable> {

  public static final String DEFAULT_CHARSET = "UTF-8";
  private volatile String defaultCharset = DEFAULT_CHARSET;

  public MetaqMessageConverter() {
    super();
  }

  public void setDefaultCharset(String defaultCharset) {
    this.defaultCharset = (defaultCharset != null) ? defaultCharset : DEFAULT_CHARSET;
  }

  @Override
  public byte[] toByteArray(Serializable body) throws MetaClientException {
    String jsonString = JSON.toJSONString(body);
    byte[] bytes = null;
    try {
      bytes = jsonString.getBytes(this.defaultCharset);
    } catch (UnsupportedEncodingException e) {
      throw new MetaClientException(e);
    }
    return bytes;
  }

  @Override
  public Serializable fromByteArray(byte[] bs) throws MetaClientException {
    String json = null;
    try {
      json = new String(bs, defaultCharset);
    } catch (UnsupportedEncodingException e) {
      throw new MetaClientException(e);
    }
    return json;
  }
}

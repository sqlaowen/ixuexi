package com.x2016.service.impl;

import javax.jws.WebService;

import com.x2016.service.WSService;

@WebService(endpointInterface = "com.x2016.service.WSService")
public class WSServiceImpl implements WSService {

  @Override
  public String xxFn(String str) {
    return str;
  }

}

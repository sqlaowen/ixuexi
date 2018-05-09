package com.x2016.poco;

import java.io.Serializable;

public class SysArea implements Serializable {
   
  private static final long serialVersionUID = 3308361070047190719L;

    private Integer areaId;

    private String areaName;

    private Integer areaPid;

    private Integer areaSeq;

    private Integer areaLevel;

    private Integer areaStatus;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    public Integer getAreaPid() {
        return areaPid;
    }

    public void setAreaPid(Integer areaPid) {
        this.areaPid = areaPid;
    }

    public Integer getAreaSeq() {
        return areaSeq;
    }

    public void setAreaSeq(Integer areaSeq) {
        this.areaSeq = areaSeq;
    }

    public Integer getAreaLevel() {
        return areaLevel;
    }

    public void setAreaLevel(Integer areaLevel) {
        this.areaLevel = areaLevel;
    }

    public Integer getAreaStatus() {
        return areaStatus;
    }

    public void setAreaStatus(Integer areaStatus) {
        this.areaStatus = areaStatus;
    }
}
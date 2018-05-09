package api.authc.single.dto.base;

import java.io.Serializable;

public abstract class SysAreaBase implements Serializable {

  private static final long serialVersionUID = 3308361070047190719L;

  // area_name
  private String areaName;

  // area_pid
  private Integer areaPid;

  // area_seq
  private Integer areaSeq;

  // area_level
  private Integer areaLevel;

  // area_status[0:可用, 1:不可用]
  private Integer areaStatus;

  /**
   * area_name
   *
   * @return
   */
  public String getAreaName() {
    return areaName;
  }

  /**
   * area_name
   *
   * @param areaName
   */
  public void setAreaName(String areaName) {
    this.areaName = areaName == null ? null : areaName.trim();
  }

  /**
   * area_pid
   *
   * @return
   */
  public Integer getAreaPid() {
    return areaPid;
  }

  /**
   * area_pid
   *
   * @param areaPid
   */
  public void setAreaPid(Integer areaPid) {
    this.areaPid = areaPid;
  }

  /**
   * area_seq
   *
   * @return
   */
  public Integer getAreaSeq() {
    return areaSeq;
  }

  /**
   * area_seq
   *
   * @param areaSeq
   */
  public void setAreaSeq(Integer areaSeq) {
    this.areaSeq = areaSeq;
  }

  /**
   * area_level
   *
   * @return
   */
  public Integer getAreaLevel() {
    return areaLevel;
  }

  /**
   * area_level
   *
   * @param areaLevel
   */
  public void setAreaLevel(Integer areaLevel) {
    this.areaLevel = areaLevel;
  }

  /**
   * area_status[0:可用, 1:不可用]
   *
   * @return
   */
  public Integer getAreaStatus() {
    return areaStatus;
  }

  /**
   * area_status[0:可用, 1:不可用]
   *
   * @param areaStatus
   */
  public void setAreaStatus(Integer areaStatus) {
    this.areaStatus = areaStatus;
  }

}

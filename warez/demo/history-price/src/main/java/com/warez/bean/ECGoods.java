package com.warez.bean;

import java.util.Date;

public class ECGoods {
    //   商品id
    private String goodsId;

    //   平台id
    private Integer platformId;

    //   商品sku
    private String skuId;

    //   商品img
    private String skuImg;

    //   商品url
    private String skuUrl;

    //   商品描述
    private String skuNote;

    //   商品状态[0:可见, 1:删除]
    private Integer skuState;

    //   所属商家
    private String skuVender;

    //   所属店铺
    private String skuShop;

    //   create_time
    private Date createTime;

    //   create_user
    private String createUser;

    //   update_time
    private Date updateTime;

    //   update_user
    private String updateUser;

    /**
     * 商品id
     *
     * @return
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 商品id
     *
     * @param goodsId
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 平台id
     *
     * @return
     */
    public Integer getPlatformId() {
        return platformId;
    }

    /**
     * 平台id
     *
     * @param platformId
     */
    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    /**
     * 商品sku
     *
     * @return
     */
    public String getSkuId() {
        return skuId;
    }

    /**
     * 商品sku
     *
     * @param skuId
     */
    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    /**
     * 商品img
     *
     * @return
     */
    public String getSkuImg() {
        return skuImg;
    }

    /**
     * 商品img
     *
     * @param skuImg
     */
    public void setSkuImg(String skuImg) {
        this.skuImg = skuImg == null ? null : skuImg.trim();
    }

    /**
     * 商品url
     *
     * @return
     */
    public String getSkuUrl() {
        return skuUrl;
    }

    /**
     * 商品url
     *
     * @param skuUrl
     */
    public void setSkuUrl(String skuUrl) {
        this.skuUrl = skuUrl == null ? null : skuUrl.trim();
    }

    /**
     * 商品描述
     *
     * @return
     */
    public String getSkuNote() {
        return skuNote;
    }

    /**
     * 商品描述
     *
     * @param skuNote
     */
    public void setSkuNote(String skuNote) {
        this.skuNote = skuNote == null ? null : skuNote.trim();
    }

    /**
     * 商品状态[0:可见, 1:删除]
     *
     * @return
     */
    public Integer getSkuState() {
        return skuState;
    }

    /**
     * 商品状态[0:可见, 1:删除]
     *
     * @param skuState
     */
    public void setSkuState(Integer skuState) {
        this.skuState = skuState;
    }

    /**
     * 所属商家
     *
     * @return
     */
    public String getSkuVender() {
        return skuVender;
    }

    /**
     * 所属商家
     *
     * @param skuVender
     */
    public void setSkuVender(String skuVender) {
        this.skuVender = skuVender == null ? null : skuVender.trim();
    }

    /**
     * 所属店铺
     *
     * @return
     */
    public String getSkuShop() {
        return skuShop;
    }

    /**
     * 所属店铺
     *
     * @param skuShop
     */
    public void setSkuShop(String skuShop) {
        this.skuShop = skuShop == null ? null : skuShop.trim();
    }

    /**
     * create_time
     *
     * @return
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * create_time
     *
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * create_user
     *
     * @return
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * create_user
     *
     * @param createUser
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    /**
     * update_time
     *
     * @return
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * update_time
     *
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * update_user
     *
     * @return
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * update_user
     *
     * @param updateUser
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }
}
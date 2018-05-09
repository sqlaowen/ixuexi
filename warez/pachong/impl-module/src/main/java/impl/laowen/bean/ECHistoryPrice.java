package impl.laowen.bean;

import java.util.Date;

public class ECHistoryPrice {
    //   价格id
    private String priceId;

    //   商品id
    private String goodsId;

    //   商品价格
    private String skuPrice;

    //   价格状态[0:可见, 1:删除]
    private Integer priceState;

    //   create_time
    private Date createTime;

    //   create_user
    private String createUser;

    /**
     * 价格id
     *
     * @return
     */
    public String getPriceId() {
        return priceId;
    }

    /**
     * 价格id
     *
     * @param priceId
     */
    public void setPriceId(String priceId) {
        this.priceId = priceId == null ? null : priceId.trim();
    }

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
     * 商品价格
     *
     * @return
     */
    public String getSkuPrice() {
        return skuPrice;
    }

    /**
     * 商品价格
     *
     * @param skuPrice
     */
    public void setSkuPrice(String skuPrice) {
        this.skuPrice = skuPrice == null ? null : skuPrice.trim();
    }

    /**
     * 价格状态[0:可见, 1:删除]
     *
     * @return
     */
    public Integer getPriceState() {
        return priceState;
    }

    /**
     * 价格状态[0:可见, 1:删除]
     *
     * @param priceState
     */
    public void setPriceState(Integer priceState) {
        this.priceState = priceState;
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
}
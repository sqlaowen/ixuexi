package impl.laowen.dao;

import impl.laowen.bean.ECHistoryPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ECHistoryPriceMapper {

    /**
     * 增加
     *
     * @param ecHistoryPrice
     * @return
     */
    int insertOne(ECHistoryPrice ecHistoryPrice);

    /**
     * 批量添加
     *
     * @param ecHistoryPriceList
     * @return
     */
    int insertBatch(@Param("ecHistoryPriceList") List<ECHistoryPrice> ecHistoryPriceList);

    /**
     * 通过goodsIdList批量查询
     *
     * @param goodsIdList
     * @return
     */
    List<String> selectBatchByGoodsIdList(@Param("goodsIdList") List<String> goodsIdList);

    /**
     * 通过goodsId查询某个商品的价格
     *
     * @param goodsId
     * @return
     */
    List<ECHistoryPrice> selectByGoodsId(String goodsId);
}
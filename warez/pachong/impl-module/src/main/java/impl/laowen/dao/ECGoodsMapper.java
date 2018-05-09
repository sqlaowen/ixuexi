package impl.laowen.dao;

import impl.laowen.bean.ECGoods;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ECGoodsMapper {

    /**
     * 添加
     *
     * @param ecGoods
     * @return
     */
    int insertOne(ECGoods ecGoods);

    /**
     * 批量添加
     *
     * @param ecGoodsList
     * @return
     */
    int insertBatch(@Param("ecGoodsList") List<ECGoods> ecGoodsList);

    /**
     * 批量查询SKU是否存在
     *
     * @param platformId
     * @param skuIdList
     * @return
     */
    List<String> selectBatchSKUListBySKUList(@Param("platformId") Integer platformId, @Param("skuIdList") List<String> skuIdList);

    /**
     * 根据skuId和platformId查
     *
     * @param skuId
     * @param platformId
     * @return
     */
    ECGoods selectBySKUAndPlatform(@Param("skuId") String skuId, @Param("platformId") Integer platformId);

    /**
     * 分页用于更新价格
     *
     * @return
     */
    List<ECGoods> selectList();

    /**
     * 更新
     *
     * @param ecGoods
     * @return
     */
    int updateById(ECGoods ecGoods);
}
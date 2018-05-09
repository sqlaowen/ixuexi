package impl.laowen.service;

import impl.laowen.bean.ECGoods;
import impl.laowen.utils.PageRes;

public interface ECGoodsService {

    /**
     * 增加
     *
     * @param ecGoods
     * @return
     */
    String insertOne(ECGoods ecGoods);

    /**
     * 分页查
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageRes<ECGoods> selectList(Integer pageNum, Integer pageSize);

}
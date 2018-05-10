package com.wgf.shop.service.imp;

import com.wgf.shop.modules.GoodsModule;
import com.wgf.shop.modules.GoodsTypeModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.modules.vo.GoodsVo;
import com.wgf.shop.repository.GoodsRepository;
import com.wgf.shop.repository.GoodsTypeRepository;
import com.wgf.shop.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 商品列表逻辑层
 */
@Service
@AllArgsConstructor
public class GoodsServiceImp implements GoodsService {

    private final GoodsTypeRepository goodsTypeRepository;

    private final GoodsRepository goodsRepository;

    /**
     * 根据用户id查询用户的分类和分类商品
     * @param accountId
     * @return
     */
    @Override
    public ResponseObject findGoodsListByTypeId(String accountId ) {
        return Optional.ofNullable(this.goodsRepository.findByAccountIdAndStatus(accountId,true))
            .map((List<GoodsModule> list) -> {
//                List<GoodsTypeModule> typeList = this.goodsTypeRepository.findByAccountId(accountId);
//                List<GoodsVo> goodsVoList = new ArrayList<GoodsVo>();
//                for(GoodsTypeModule entity : typeList){
//                    GoodsVo goodsVo = new GoodsVo();
//                    goodsVo.setType(entity);
//                    List<GoodsModule> goodsList = new ArrayList<>();
//                    for(GoodsModule goods : list){
//                        goodsList.add(goods);
//                    }
//                    goodsVo.setGoods(goodsList);
//                    goodsVoList.add(goodsVo);
//                }
                return new ResponseObject().success("产品列表",list);
            }).orElse(new ResponseObject().success("产品列表",null));
    }
}

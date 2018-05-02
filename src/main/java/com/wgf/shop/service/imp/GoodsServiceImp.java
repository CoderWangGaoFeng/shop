package com.wgf.shop.service.imp;

import com.wgf.shop.modules.GoodsModule;
import com.wgf.shop.modules.GoodsTypeModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.modules.enmu.RequestStatus;
import com.wgf.shop.modules.vo.Goods;
import com.wgf.shop.repository.GoodsRepository;
import com.wgf.shop.repository.GoodsTypeRepository;
import com.wgf.shop.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
                return new ResponseObject().success("产品列表",list);
            }).orElse(new ResponseObject().success("产品列表",null));
    }
}
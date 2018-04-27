package com.wgf.shop.service.imp;

import com.wgf.shop.modules.GoodsTypeModule;
import com.wgf.shop.modules.ResponseObject;
import com.wgf.shop.repository.GoodsTypeRepository;
import com.wgf.shop.service.GoodsTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 产品分类逻辑层
 */
@Service
@AllArgsConstructor
public class GoodsTypeServiceImp implements GoodsTypeService {

    private final GoodsTypeRepository goodsTypeRepository;
    /**
     * 根据用户查询食品分类
     * @param accountId
     * @return
     */
    @Override
    public ResponseObject findListByAccount(String accountId) {
        return Optional.ofNullable(this.goodsTypeRepository.findByAccountId(accountId))
                .map((List<GoodsTypeModule> list ) -> {
                    return new ResponseObject().success("产品分类",list);
                }).orElse(new ResponseObject().success("产品分类",null));
    }
}

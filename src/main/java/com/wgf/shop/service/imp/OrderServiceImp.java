package com.wgf.shop.service.imp;

import com.wgf.shop.modules.*;
import com.wgf.shop.modules.enmu.OrderStatus;
import com.wgf.shop.modules.vo.OrderVo;
import com.wgf.shop.repository.AddressRepository;
import com.wgf.shop.repository.GoodsRepository;
import com.wgf.shop.repository.OrderGoodsRepository;
import com.wgf.shop.repository.OrderRepository;
import com.wgf.shop.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 订单逻辑层
 * @author wanggf
 */
@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;

    private final OrderGoodsRepository orderGoodsRepository;

    private final AddressRepository addressRepository;

    private final GoodsRepository goodsRepository;


    /**
     * 新生成订单
     * @return
     */
    @Override
    public ResponseObject insertOrder(OrderVo entity) {
        /**
         * 1.保存总订单
         * 2.保存分订单
         * 3.在微信端生成支付订单
         */
        Set<Long> goodsId = entity.getGoods().keySet();
        List<GoodsModule> goodsList = this.goodsRepository.findByIdIn(goodsId);
        BigDecimal sumPrice = new BigDecimal("0.00");
        List<OrderGoodsModule> list = new ArrayList<OrderGoodsModule>();
        for( GoodsModule goods : goodsList){
            BigDecimal sum = goods.getPrice().multiply(new BigDecimal(entity.getGoods().get(goods.getId())));
            sumPrice = sumPrice.add(sum);
            OrderGoodsModule orderGoods = new OrderGoodsModule()
                    .setGoodId(goods.getId())
                    .setName(goods.getName())
                    .setPrice(goods.getPrice()+"");
            list.add(orderGoods);
        }
        this.orderGoodsRepository.saveAll(list);
        AddressModule address = this.addressRepository.findById(Long.parseLong(entity.getAddressId())).get();
        OrderModule order = new OrderModule().setAccountId(entity.getAccountId())
                .setAddress(address.getAddress())
                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                .setName(address.getName())
                .setOpenId(entity.getOpenId())
                .setPhone(address.getPhone())
                .setPrice(sumPrice)
                .setStatus(OrderStatus.WAIT_PAY);
        this.orderRepository.save(order);
        return new ResponseObject().success("请求成功",null);
    }
}
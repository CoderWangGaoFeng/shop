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
import java.util.*;

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
        Map<Long ,Long> map = new HashMap<Long ,Long>();
        for(OrderGoodsModule orderGoods : entity.getOrderGoods()){
            map.put(orderGoods.getId(),orderGoods.getNum());
        }
        Set<Long> goodsId = map.keySet();
        List<GoodsModule> goodsList = this.goodsRepository.findByIdIn(goodsId);
        BigDecimal sumPrice = new BigDecimal("0.00");
        List<OrderGoodsModule> list = new ArrayList<OrderGoodsModule>();
        for( GoodsModule goods : goodsList){
            BigDecimal sum = goods.getPrice().multiply(new BigDecimal(map.get(goods.getId())));
            sumPrice = sumPrice.add(sum);
            OrderGoodsModule orderGoods = new OrderGoodsModule()
                    .setGoodId(goods.getId())
                    .setName(goods.getName())
                    .setPrice(goods.getPrice()+"")
                    .setNum(map.get(goods.getId()));
            list.add(orderGoods);
        }
        AddressModule address = this.addressRepository.findById(Long.parseLong(entity.getAddressId())).get();
        OrderModule order = new OrderModule().setAccountId(entity.getAccountId())
                .setAddress(address.getAddress())
                .setCreateTime(new Timestamp(System.currentTimeMillis()))
                .setName(address.getName())
                .setOpenId(entity.getOpenId())
                .setPhone(address.getPhone())
                .setPrice(sumPrice)
                .setStatus(OrderStatus.WAIT_PAY);
        order = this.orderRepository.save(order);
        for(OrderGoodsModule goods : list){
            goods.setOrderId(order.getId());
        }
        this.orderGoodsRepository.saveAll(list);
        return new ResponseObject().success("请求成功",null);
    }

    /**
     * 分页查询订单信息
     * @param openId
     * @param page
     * @return
     */
    @Override
    public ResponseObject findOrderByPage(String openId,String accountId, String page) {
        List<OrderModule> orderList = this.orderRepository.findByOpenIdAndAccountIdOrderByCreateTimeDesc(openId,accountId);
        List<OrderVo> voList = new ArrayList<>();
        if(orderList != null && orderList.size() > 0 ){
            for(OrderModule entity : orderList){
                BigDecimal sum = new BigDecimal("0.00");//重新计算总价，防止价格变动
                OrderVo vo = new OrderVo();
//                List<OrderGoodsModule> list = this.orderGoodsRepository.findByOrderId(entity.getId());
                List<GoodsModule> list = this.goodsRepository.findByOrderId(entity.getId());
                for(GoodsModule goods : list){
                    OrderGoodsModule orderGoodsEntity = this.orderGoodsRepository.
                            findByOrderIdAndGoodId(entity.getId(),goods.getId());
                    goods.setNum(orderGoodsEntity.getNum()+"");
                    sum = sum.add(goods.getPrice().multiply(new BigDecimal(goods.getNum())));
                }
                entity.setPrice(sum);
                vo.setOrder(entity);
                vo.setTime(entity.getCreateTime().toString());
                vo.setGoods(list);
                voList.add(vo);
            }
            return new ResponseObject().success("查询成功",voList);
        }else{
            return new ResponseObject().success("还没有订单哦",null);
        }
    }

    @Override
    public ResponseObject delOrder(String id) {
        /*
        -- 需要校验openId来确定订单是否是该用户
        1.查询订单状态，只能删除未付款的订单
        2.删除订单下商品列表
         */
        OrderModule order = this.orderRepository.findById(Long.parseLong(id)).get();
        if(order != null && order.getStatus().equals(OrderStatus.WAIT_PAY)){
            List<OrderGoodsModule> orderGoodsList = this.orderGoodsRepository.findByOrderId(order.getId());
            this.orderGoodsRepository.deleteAll(orderGoodsList);
            this.orderRepository.delete(order);
            return  new ResponseObject().success("删除成功",null);
        }else{
            return  new ResponseObject().fail("只能删除待付款订单",null);
        }
    }
}
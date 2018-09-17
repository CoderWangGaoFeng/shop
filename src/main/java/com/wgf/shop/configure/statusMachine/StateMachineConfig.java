package com.wgf.shop.configure.statusMachine;

import com.wgf.shop.configure.statusMachine.statusMachineEnum.OrderEventEnum;
import com.wgf.shop.configure.statusMachine.statusMachineEnum.OrderStatusEnum;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

/**
 * 状态机配置类
 * @Author wanggaofeng
 * @Date 2018/8/28 16:01
 */
//@Configuration
//@EnableStateMachine//该注解标识启用状态机（Spring StateMechine功能）
public class StateMachineConfig extends EnumStateMachineConfigurerAdapter<OrderStatusEnum,OrderEventEnum> {

    /**
     * 初始化状态机
     **/
    @Override
    public void configure(StateMachineStateConfigurer<OrderStatusEnum, OrderEventEnum> states) throws Exception {
        states.withStates()
            //定义初始状态（初始状态）
            .initial(OrderStatusEnum.WAITPAY)
                //定义状态机状态（所有状态）
                .states(EnumSet.allOf(OrderStatusEnum.class));
//        super.configure(states);
    }

    //初始化状态迁移事件
    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatusEnum, OrderEventEnum> transitions) throws Exception {
//        super.configure(transitions);
        transitions
                .withExternal()
                .source(OrderStatusEnum.WAITPAY)//状态机初始状态
                .target(OrderStatusEnum.WAITSEND)//状态机目标状态
                .event(OrderEventEnum.ORDERPAY);//状态改变事件
               // .
    }
}

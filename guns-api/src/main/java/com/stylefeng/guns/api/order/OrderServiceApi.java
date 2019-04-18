package com.stylefeng.guns.api.order;

import com.stylefeng.guns.api.order.model.OrderVo;

import java.util.List;

// 订单接口
public interface OrderServiceApi {

    // 验证售出的票是否为真
    boolean isTrueSeats(String filedId, String seats);
    // 验证是否为售出
    boolean isNotSoldSeats(String filedId, String seats);

    // 创建订单
    OrderVo saveOrder(String filedId, String seats, String seatsName, Integer userId);

    // 获取当前登录用户的购票详情
    List<OrderVo> getOrderByUserId(Integer userId);

    // 根据场次 id 获取售出的座位编号
    String getSoldSeatsByFiledId(String filedId);


}

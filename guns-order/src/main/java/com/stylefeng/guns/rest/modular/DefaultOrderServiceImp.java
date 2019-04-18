package com.stylefeng.guns.rest.modular;

import com.alibaba.dubbo.config.annotation.Service;
import com.stylefeng.guns.api.order.OrderServiceApi;
import com.stylefeng.guns.api.order.model.OrderVo;
import com.stylefeng.guns.rest.common.persistence.dao.MoocOrderTMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Service(interfaceClass = OrderServiceApi.class)
public class DefaultOrderServiceImp implements OrderServiceApi{

    @Autowired
    private MoocOrderTMapper orderTMapper;

    @Override
    public boolean isTrueSeats(String filedId, String seats) {
        return false;
    }

    @Override
    public boolean isNotSoldSeats(String filedId, String seats) {
        return false;
    }

    @Override
    public OrderVo saveOrder(String filedId, String seats, String seatsName, Integer userId) {
        return null;
    }

    @Override
    public List<OrderVo> getOrderByUserId(Integer userId) {
        return null;
    }

    @Override
    public String getSoldSeatsByFiledId(String filedId) {
        return null;
    }
}

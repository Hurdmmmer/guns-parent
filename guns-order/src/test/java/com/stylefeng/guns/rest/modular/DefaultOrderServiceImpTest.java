package com.stylefeng.guns.rest.modular;

import com.stylefeng.guns.api.order.model.OrderVo;
import com.stylefeng.guns.rest.GunsOrderApplication;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest(classes = GunsOrderApplication.class)
@RunWith(SpringRunner.class)
public class DefaultOrderServiceImpTest {
    @Autowired
    DefaultOrderServiceImp defaultOrderServiceImp;

    @Test
    public void isTrueSeats() {
        boolean trueSeats = defaultOrderServiceImp.isTrueSeats("1", "1,2,3");
        System.out.println("trueSeats = " + trueSeats);
    }

    @Test
    public void isNotSoldSeats() {
        defaultOrderServiceImp.isNotSoldSeats("1", "9,10");
    }

    @Test
    public void saveOrder() {
    }

    @Test
    public void getOrderByUserId() {
        List<OrderVo> orderByUserId = defaultOrderServiceImp.getOrderByUserId(1);
        System.out.println("orderByUserId = " + orderByUserId);
    }

    @Test
    public void getSoldSeatsByFiledId() {
        String soldSeatsByFiledId = this.defaultOrderServiceImp.getSoldSeatsByFiledId("1");
        System.out.println("soldSeatsByFiledId = " + soldSeatsByFiledId);
    }
}
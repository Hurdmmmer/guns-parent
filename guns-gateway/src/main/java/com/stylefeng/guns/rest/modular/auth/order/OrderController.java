package com.stylefeng.guns.rest.modular.auth.order;

import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @PostMapping("/buyTickets")
    public ResponseVo buyTickets(Integer fieldId, String soldSeats, String seatsName) {

        return null;
    }

    @PostMapping("/getOrderInfo")
    public ResponseVo getOrderInfo(@RequestParam(value = "nowPage", required = false, defaultValue = "1") Integer nowPage,
                                   @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {


        return null;
    }

}

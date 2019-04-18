package com.stylefeng.guns.rest.modular.auth.order.vo;

import lombok.Data;

@Data
public class OrderResponseVo {
    private String orderId;
    private String filmName;
    private String fieldTime;
    private String cinemaTime;
    private String seatsName;
    private String orderPrice;
    private long orderTimestamp;
    private String orderStatus;
}

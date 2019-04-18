package com.stylefeng.guns.api.order.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderVo implements Serializable {
    private String orderId;
    private String filmName;
    private String fieldTime;
    private String cinemaName;
    private String seatsName;
    private String orderPrice;
    private long orderTimestamp;
    private String orderStatus;

}

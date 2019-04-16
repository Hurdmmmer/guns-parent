package com.stylefeng.guns.api.cinema.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class CinemaVo implements Serializable {
    private String uuid;
    private String cinemaName;
    private String address;
    private BigDecimal minimumPrice;

}

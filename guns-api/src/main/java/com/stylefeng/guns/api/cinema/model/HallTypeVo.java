package com.stylefeng.guns.api.cinema.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class HallTypeVo implements Serializable {
    private Integer halltypeId;
    private String halltypeName;
    private boolean isActive;
}

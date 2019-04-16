package com.stylefeng.guns.api.cinema.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class FilmFieldVo implements Serializable {
    private Integer fieldId;
    private String beginTime;
    private String endTime;
    private String language;
    private String hallName;
    private String price;
}

package com.stylefeng.guns.api.film.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class CatVo implements Serializable {
    private String catId;
    private String catName;
    private boolean isActive;
}

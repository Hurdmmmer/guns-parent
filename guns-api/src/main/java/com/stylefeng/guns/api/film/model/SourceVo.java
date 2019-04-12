package com.stylefeng.guns.api.film.model;

import lombok.Data;

import java.io.Serializable;
@Data
public class SourceVo implements Serializable {
    private String sourceId;
    private String sourceName;
    private boolean isActive;
}

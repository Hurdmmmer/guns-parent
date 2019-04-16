package com.stylefeng.guns.api.cinema.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class FilmInfoVo implements Serializable {
    private Integer filmId;
    private String filmName;
    private String filmLength;
    private String filmType;
    private String filmCats;
    private String actors;
    private List<FilmFieldVo> filmFields;

}

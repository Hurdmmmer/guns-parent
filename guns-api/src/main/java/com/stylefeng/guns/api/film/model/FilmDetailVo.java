package com.stylefeng.guns.api.film.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmDetailVo implements Serializable {
    private Integer filmId;
    private String filmName;
    private String filmEnName;
    private String imgAddress;
    private String score;
    private String scoreNum;
    private String totalBox;
    private String info01;
    private String info02;
    private String info03;

    private FilmDescVo info04;


}

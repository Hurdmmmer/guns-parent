package com.stylefeng.guns.api.film.model;

import lombok.Data;

import java.io.Serializable;

/** film info detail */
@Data
public class FilmInfo implements Serializable {
    private String filmId;
    private Integer filmType;
    private String imgAddress;
    private String filmName;
    private String filmScore;
    /*喜欢人数*/
    private Integer expectNum;
    /*上映时间*/
    private String showTime;
    /*排名*/
    private Integer boxNum;

}

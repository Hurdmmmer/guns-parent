package com.stylefeng.guns.api.film.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmDescVo implements Serializable {

    private String biography;
    private String filmId;
    private ActorsVo actors;
    private ImgVo imgs;


}

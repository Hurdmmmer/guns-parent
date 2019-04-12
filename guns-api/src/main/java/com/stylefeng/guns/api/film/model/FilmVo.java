package com.stylefeng.guns.api.film.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *  电影 view model
 */
@Data
public class FilmVo implements Serializable {
    private int filmsNum;
    private int nowPage;
    private int totalPage;
    private List<FilmInfo> films;
}

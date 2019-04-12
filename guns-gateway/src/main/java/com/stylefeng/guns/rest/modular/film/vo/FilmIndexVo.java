package com.stylefeng.guns.rest.modular.film.vo;

import com.stylefeng.guns.api.film.model.BannerVo;
import com.stylefeng.guns.api.film.model.FilmInfo;
import com.stylefeng.guns.api.film.model.FilmVo;
import lombok.Data;

import java.util.List;

/** 主页 view model */
@Data
public class FilmIndexVo {

    private List<BannerVo> banners;

    private FilmVo hotFilms;

    private FilmVo soonFilms;

    private List<FilmInfo> boxRanking;

    private List<FilmInfo> expectRanking;

    private List<FilmInfo> top100;

}

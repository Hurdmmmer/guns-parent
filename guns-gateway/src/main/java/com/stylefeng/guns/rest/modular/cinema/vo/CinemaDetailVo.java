package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.model.CinemaInfoVo;
import com.stylefeng.guns.api.cinema.model.FilmInfoVo;
import lombok.Data;

import java.util.List;

@Data
public class CinemaDetailVo {
    private CinemaInfoVo cinemaInfo;
    private List<FilmInfoVo> filmList;
}

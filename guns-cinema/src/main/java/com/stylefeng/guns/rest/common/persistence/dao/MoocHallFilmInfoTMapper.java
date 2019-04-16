package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.api.cinema.model.FilmInfoVo;
import com.stylefeng.guns.rest.common.persistence.model.MoocHallFilmInfoT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 影厅电影信息表 Mapper 接口
 * </p>
 *
 * @author Youjian
 * @since 2019-04-12
 */
public interface MoocHallFilmInfoTMapper extends BaseMapper<MoocHallFilmInfoT> {
    List<FilmInfoVo> findCinemaFilm(@Param("cinemaId") Integer cinemaId);

    FilmInfoVo findFilmById(@Param("filmId") Integer filmId);
}

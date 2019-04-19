package com.stylefeng.guns.api.cinema;

import com.stylefeng.guns.api.cinema.model.*;

import java.util.List;

public interface CinemaServiceApi {

    /**
     * 根据条件查询影院信息
     * @param cinemaId 影院编号
     * @param areaId 区域编号
     * @param hallId 大厅编号
     * @return 影院集合
     */
    List<CinemaVo> findCinema(String cinemaId, String areaId, String hallId, Integer nowPage, Integer pageSize);

    Integer getCount(String cinemaId, String areaId, String hallId);

    /**
     *  获取所有的区域
     */
    List<AreaVo> getAreas();
    /** 获取所有的影厅类型 */
    List<HallTypeVo> getHallType();
    /** 获取所有的品牌影院 */
    List<BrandVo> getBrand();
    /** 根据id查询影院信息 */
    CinemaInfoVo getCinemaInfo(Integer cinemaId);

    List<FilmInfoVo> getCinemaFilm(Integer cinemaId);

    /** 查询电影信息 */
    FilmInfoVo getFilmInfoByFilmId(Integer filmId);

    /**
     * 查询电影场次详情
     */
    HallInfoVo getHallInfo(Integer fieldId);

    /**
     * 根据影院和场次ID查询电影信息 |
     */
    FilmInfoVo getFilmBy(Integer fieldId);
}

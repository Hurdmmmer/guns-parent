package com.stylefeng.guns.api.film;

import com.stylefeng.guns.api.film.model.*;

import java.util.List;

public interface FilmServiceAPI {
    /**
     * 获取横幅广告
     */
    List<BannerVo> getBanners();

    /**
     *  获取热门电影
     * @param isLimit 是否限制返回数量
     * @param num isLimit 为 true 取返回数量
     * @return 热门电影
     */
    FilmVo getHotFilms(boolean isLimit, int num);

    /**
     *  获取即将上映
     * @param isLimit 是否限制返回数量
     * @param num isLimit 为 true 取返回数量
     * @return 即将上映的电影 【默认按受欢迎程度排序】
     */
    FilmVo getSoonFilms(boolean isLimit, int num);

    /**
     * 获取票房排行榜
     */
    List<FilmInfo> getBoxRanking();
    /**
     * 获取人气排行榜
     */
    List<FilmInfo> getExpectRanking();
    /**
     * 获取Top 100
     */
    List<FilmInfo> getTop();

    /** 获取电影年代 */
    List<YearVo> getYears(Integer id);

    /** 获取电影类别 */
    List<CatVo> getCats(Integer id);

    /**
     * 获取电影区域
     */
    List<SourceVo> getSource(Integer id);

    FilmVo getFilm(int showType,int sortId, int catId, int sourceId, int yearId, int page, int pageSize);

    FilmDetailVo getFilm(String searchParam, int searchType);

    FilmDescVo getFilmDesc(String uuid);

}

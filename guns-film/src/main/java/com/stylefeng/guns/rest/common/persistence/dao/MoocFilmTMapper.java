package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.api.cinema.model.FilmFieldVo;
import com.stylefeng.guns.api.film.model.FilmDetailVo;
import com.stylefeng.guns.rest.common.persistence.model.MoocFilmT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 影片主表 Mapper 接口
 * </p>
 *
 * @author Youjian
 * @since 2019-04-10
 */
public interface MoocFilmTMapper extends BaseMapper<MoocFilmT> {

    FilmDetailVo getFilmDetailById(@Param("id")Integer id);

    FilmDetailVo getFilmDetailByName(@Param("name") String name);

}

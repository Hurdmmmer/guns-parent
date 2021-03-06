package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.api.film.model.ActorVo;
import com.stylefeng.guns.rest.common.persistence.model.MoocActorT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 演员表 Mapper 接口
 * </p>
 *
 * @author Youjian
 * @since 2019-04-10
 */
public interface MoocActorTMapper extends BaseMapper<MoocActorT> {

    List<ActorVo> getActorByFilmId(@Param("filmId") String filmId);

}

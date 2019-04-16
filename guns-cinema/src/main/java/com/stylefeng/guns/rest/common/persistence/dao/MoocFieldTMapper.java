package com.stylefeng.guns.rest.common.persistence.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocFieldT;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 放映场次表 Mapper 接口
 * </p>
 *
 * @author Youjian
 * @since 2019-04-12
 */
public interface MoocFieldTMapper extends BaseMapper<MoocFieldT> {
    MoocFieldT getMoocFieldTByFieldIdAndCinemaId(@Param("fieldId") Integer fieldId, @Param("cinemaId") Integer cinemaId);
}

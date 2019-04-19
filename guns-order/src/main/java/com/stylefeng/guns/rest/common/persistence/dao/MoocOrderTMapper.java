package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.api.cinema.model.FilmFieldVo;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单信息表 Mapper 接口
 * </p>
 *
 * @author Youjian
 * @since 2019-04-17
 */
public interface MoocOrderTMapper extends BaseMapper<MoocOrderT> {
    String getFieldSeatsPath(@Param("fieldId") Integer fieldId);

    List<MoocOrderT> getSoldSeatsIds(@Param("fieldId") Integer fieldId);

    List<MoocOrderT> selectByOrderUser(@Param("userId") Integer userId);

    FilmFieldVo getFieldInfo(@Param("fieldId") Integer fieldId);

}

package com.stylefeng.guns.rest.modular.cinema.vo;

import com.stylefeng.guns.api.cinema.model.AreaVo;
import com.stylefeng.guns.api.cinema.model.BrandVo;
import com.stylefeng.guns.api.cinema.model.HallTypeVo;
import lombok.Data;

import java.util.List;

@Data
public class CinemaConditionVo {
    private List<BrandVo> brandList;
    private List<AreaVo> areaList;
    private List<HallTypeVo> halltypeList;

}

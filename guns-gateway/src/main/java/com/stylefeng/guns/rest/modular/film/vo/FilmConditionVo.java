package com.stylefeng.guns.rest.modular.film.vo;

import com.stylefeng.guns.api.film.model.CatVo;
import com.stylefeng.guns.api.film.model.SourceVo;
import com.stylefeng.guns.api.film.model.YearVo;
import lombok.Data;

import java.util.List;

/** 类型标签对象 Vo*/
@Data
public class FilmConditionVo {

    List<CatVo> catInfo;
    List<SourceVo> sourceInfo;
    List<YearVo> yearInfo;

}

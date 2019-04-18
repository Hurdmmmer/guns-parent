package com.stylefeng.guns.rest.modular;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.cinema.CinemaServiceApi;
import com.stylefeng.guns.api.cinema.model.*;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = CinemaServiceApi.class)
public class DefaultCinemaServiceImpl implements CinemaServiceApi {

    @Autowired
    private MoocCinemaTMapper moocCinemaTMapper;

    @Autowired
    private MoocAreaDictTMapper moocAreaDictTMapper;

    @Autowired
    private MoocBrandDictTMapper moocBrandDictTMapper;

    @Autowired
    private MoocHallDictTMapper moocHallDictTMapper;

    @Autowired
    private MoocHallFilmInfoTMapper moocHallFilmInfoTMapper;

    @Autowired
    private MoocFieldTMapper moocFieldTMapper;


    @Override
    public List<CinemaVo> findCinema(String cinemaId, String areaId, String hallId, Integer nowPage, Integer pageSize) {
        EntityWrapper<MoocCinemaT> entityWrapper = getMoocCinemaTEntityWrapper(cinemaId, areaId, hallId);
        Page<MoocCinemaT> page = new Page<>();
        List<MoocCinemaT> moocCinemaTS = this.moocCinemaTMapper.selectPage(page, entityWrapper);
        List<CinemaVo> cinemaVos = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(moocCinemaTS)) {
            for (MoocCinemaT moocCinemaT : moocCinemaTS) {
                CinemaVo cinemaVo = CinemaVo.builder().address(moocCinemaT.getCinemaAddress())
                        .cinemaName(moocCinemaT.getCinemaName())
                        .minimumPrice(new BigDecimal(moocCinemaT.getMinimumPrice()))
                        .uuid(moocCinemaT.getUuid() + "").build();
                cinemaVos.add(cinemaVo);
            }
        }

        return cinemaVos;
    }

    private EntityWrapper<MoocCinemaT> getMoocCinemaTEntityWrapper(String cinemaId, String areaId, String hallId) {
        EntityWrapper<MoocCinemaT> entityWrapper = new EntityWrapper<>();

        if (99 != Integer.valueOf(cinemaId)) {
            entityWrapper.eq("uuid", Integer.valueOf(cinemaId));
        }
        if (99 != Integer.valueOf(areaId)) {
            entityWrapper.eq("area_id", Integer.valueOf(areaId));
        }
        if (99 != Integer.valueOf(hallId)) {
            hallId = "%#" + hallId + "#%";
            entityWrapper.like("hall_ids", hallId);
        }
        return entityWrapper;
    }

    @Override
    public Integer getCount(String cinemaId, String areaId, String hallId) {
        EntityWrapper<MoocCinemaT> entityWrapper = getMoocCinemaTEntityWrapper(cinemaId, areaId, hallId);
        return this.moocCinemaTMapper.selectCount(entityWrapper);
    }

    @Override
    public List<AreaVo> getAreas() {
        List<MoocAreaDictT> moocAreaDictTS = this.moocAreaDictTMapper.selectList(null);
        List<AreaVo> areaVos = new ArrayList<>();
        for (MoocAreaDictT moocAreaDictT : moocAreaDictTS) {
            AreaVo areaVo = new AreaVo();
            areaVo.setAreaId(moocAreaDictT.getUuid());
            areaVo.setAreaName(moocAreaDictT.getShowName());
            areaVos.add(areaVo);
        }
        return areaVos;
    }

    @Override
    public List<HallTypeVo> getHallType() {
        List<MoocHallDictT> moocHallDictTS = this.moocHallDictTMapper.selectList(null);
        List<HallTypeVo> hallTypeVos = new ArrayList<>();
        for (MoocHallDictT moocHallDictT : moocHallDictTS) {
            HallTypeVo hallTypeVo = new HallTypeVo();
            hallTypeVo.setHalltypeId(moocHallDictT.getUuid());
            hallTypeVo.setHalltypeName(moocHallDictT.getShowName());
            hallTypeVos.add(hallTypeVo);
        }
        return hallTypeVos;
    }

    @Override
    public List<BrandVo> getBrand() {
        List<MoocBrandDictT> moocBrandDictTS = this.moocBrandDictTMapper.selectList(null);
        List<BrandVo> brandVos = new ArrayList<>();
        for (MoocBrandDictT moocBrandDictT : moocBrandDictTS) {
            BrandVo brandVo = new BrandVo();
            brandVo.setBrandId(moocBrandDictT.getUuid());
            brandVo.setBrandName(moocBrandDictT.getShowName());
            brandVos.add(brandVo);
        }
        return brandVos;
    }

    @Override
    public CinemaInfoVo getCinemaInfo(Integer cinemaId) {
        MoocCinemaT moocCinemaT = this.moocCinemaTMapper.selectById(cinemaId);
        CinemaInfoVo cinemaInfoVo = new CinemaInfoVo();
        cinemaInfoVo.setCinemaId(moocCinemaT.getUuid());
        cinemaInfoVo.setCinemaAddress(moocCinemaT.getCinemaAddress());
        cinemaInfoVo.setImgUrl(moocCinemaT.getImgAddress());
        cinemaInfoVo.setCinemaPhone(moocCinemaT.getCinemaPhone());
        cinemaInfoVo.setCinemaName(moocCinemaT.getCinemaName());
        return cinemaInfoVo;
    }

    @Override
    public List<FilmInfoVo> getCinemaFilm(Integer cinemaId) {
        return this.moocHallFilmInfoTMapper.findCinemaFilm(cinemaId);
    }

    @Override
    public FilmInfoVo getFilmInfoByFilmId(Integer filmId) {
        MoocHallFilmInfoT moocHallFilmInfoT = moocHallFilmInfoTMapper.selectById(filmId);
        if (moocHallFilmInfoT == null) {
            return null;
        }
        FilmInfoVo filmInfoVo = new FilmInfoVo();

        filmInfoVo.setActors(moocHallFilmInfoT.getActors());
        filmInfoVo.setFilmType(moocHallFilmInfoT.getFilmLanguage());
        filmInfoVo.setFilmName(moocHallFilmInfoT.getFilmName());
        filmInfoVo.setFilmLength(moocHallFilmInfoT.getFilmLength());
        filmInfoVo.setFilmId(moocHallFilmInfoT.getFilmId());
        filmInfoVo.setFilmCats(moocHallFilmInfoT.getFilmCats());
        return filmInfoVo;
    }

    @Override
    public HallInfoVo getHallInfo(Integer cinemaId, Integer fieldId) {
        MoocFieldT moocFieldT = this.moocFieldTMapper.getMoocFieldTByFieldIdAndCinemaId(fieldId, cinemaId);
        if (moocFieldT == null) {
            return null;
        }
        MoocHallDictT moocHallDictT = this.moocHallDictTMapper.selectById(moocFieldT.getHallId());

        HallInfoVo hallInfoVo = new HallInfoVo();

        hallInfoVo.setSeatFile(moocHallDictT.getSeatAddress());
        hallInfoVo.setPrice(moocFieldT.getPrice() + "");
        return hallInfoVo;
    }

    @Override
    public FilmInfoVo getFilmBy(Integer cinemaId, Integer fieldId) {
        MoocFieldT moocFieldT = this.moocFieldTMapper.getMoocFieldTByFieldIdAndCinemaId(fieldId, cinemaId);
        if (moocFieldT == null) {
            return null;
        }
        return this.moocHallFilmInfoTMapper.findFilmById(moocFieldT.getFilmId());
    }
}

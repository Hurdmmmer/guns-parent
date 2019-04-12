package com.stylefeng.guns.rest.modular;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.model.*;
import com.stylefeng.guns.core.util.DateUtil;
import com.stylefeng.guns.rest.common.persistence.dao.*;
import com.stylefeng.guns.rest.common.persistence.model.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Service(interfaceClass = FilmServiceAPI.class)
public class DefaultFilmServiceIml implements FilmServiceAPI {

    @Autowired
    private MoocBannerTMapper bannerTMapper;

    @Autowired
    private MoocFilmTMapper moocFilmTMapper;

    @Autowired
    private MoocYearDictTMapper moocYearDictTMapper;
    @Autowired
    private MoocCatDictTMapper moocCatDictTMapper;
    @Autowired
    private MoocSourceDictTMapper moocSourceDictTMapper;

    @Autowired
    private MoocActorTMapper moocActorTMapper;

    @Autowired
    private MoocFilmInfoTMapper moocFilmInfoTMapper;


    @Override

    public List<BannerVo> getBanners() {
        List<MoocBannerT> moocBannerTS = bannerTMapper.selectList(null);
        List<BannerVo> bannerVos = new ArrayList<>();
        for (MoocBannerT moocBannerT : moocBannerTS) {
            BannerVo bannerVo = new BannerVo();
            bannerVo.setBannerAddress(moocBannerT.getBannerAddress());
            bannerVo.setBannerId(moocBannerT.getUuid() + "");
            bannerVo.setBannerUrl(moocBannerT.getBannerUrl());
            bannerVos.add(bannerVo);
        }
        return bannerVos;
    }


    private List<FilmInfo> getFilmInfos(List<MoocFilmT> moocFilmTS) {
        List<FilmInfo> films = new ArrayList<>();
        for (MoocFilmT moocFilmT : moocFilmTS) {
            FilmInfo film = new FilmInfo();
            film.setShowTime(DateUtil.getDay(moocFilmT.getFilmTime()));
            film.setImgAddress(moocFilmT.getImgAddress());
            film.setFilmType(moocFilmT.getFilmType());
            film.setFilmScore(moocFilmT.getFilmScore());
            film.setFilmName(moocFilmT.getFilmName());
            film.setFilmId(moocFilmT.getUuid() + "");
            film.setExpectNum(moocFilmT.getFilmPresalenum());
            film.setBoxNum(moocFilmT.getFilmBoxOffice());

            films.add(film);
        }
        return films;
    }

    @Override
    public FilmVo getHotFilms(boolean isLimit, int num) {
        FilmVo filmVo = new FilmVo();
        EntityWrapper<MoocFilmT> conditional = new EntityWrapper<>();
        conditional.eq("film_status", 1);
        List<FilmInfo> films = null;
        if (isLimit) {
            // 首页使用, 不分页
            Page<MoocFilmT> page = new Page<>(1, num);
            List<MoocFilmT> moocFilmTS = this.moocFilmTMapper.selectPage(page, conditional);
            films = getFilmInfos(moocFilmTS);
            filmVo.setFilms(films);
            filmVo.setFilmsNum(films.size());

        } else {
            // 页面分页使用
        }

        return filmVo;
    }

    @Override
    public FilmVo getSoonFilms(boolean isLimit, int num) {
        FilmVo filmVo = new FilmVo();
        EntityWrapper<MoocFilmT> conditional = new EntityWrapper<>();
        conditional.eq("film_status", 2);
        List<FilmInfo> films = null;
        if (isLimit) {
            // 首页使用, 不分页
            Page<MoocFilmT> page = new Page<>(1, num);
            List<MoocFilmT> moocFilmTS = this.moocFilmTMapper.selectPage(page, conditional);
            films = getFilmInfos(moocFilmTS);
            filmVo.setFilms(films);
            filmVo.setFilmsNum(films.size());

        } else {
            // 页面分页使用
        }

        return filmVo;
    }

    @Override
    public List<FilmInfo> getBoxRanking() {
        Page<MoocFilmT> page = new Page<>(1, 10, "film_box_office");
        List<MoocFilmT> moocFilmTS = this.moocFilmTMapper.selectPage(page, null);
        return getFilmInfos(moocFilmTS);
    }

    @Override
    public List<FilmInfo> getExpectRanking() {
        Page<MoocFilmT> page = new Page<>(1, 10, "film_preSaleNum");
        List<MoocFilmT> moocFilmTS = this.moocFilmTMapper.selectPage(page, null);
        return getFilmInfos(moocFilmTS);
    }

    @Override
    public List<FilmInfo> getTop() {
        Page<MoocFilmT> page = new Page<>(1, 10, "film_score");
        List<MoocFilmT> moocFilmTS = this.moocFilmTMapper.selectPage(page, null);
        return getFilmInfos(moocFilmTS);
    }

    @Override
    public List<YearVo> getYears(Integer id) {
        List<MoocYearDictT> moocYearDictTS = null;
        if (id == 99) {
            moocYearDictTS = this.moocYearDictTMapper.selectList(null);
        } else {
            EntityWrapper<MoocYearDictT> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("uuid", id);
            moocYearDictTS = this.moocYearDictTMapper.selectList(entityWrapper);
        }
        List<YearVo> result = null;
        if (CollectionUtils.isNotEmpty(moocYearDictTS)) {
            result = new ArrayList<>();
            for (MoocYearDictT moocYearDictT : moocYearDictTS) {
                YearVo yearVo = new YearVo();
                yearVo.setYearName(moocYearDictT.getShowName());
                yearVo.setYearId(moocYearDictT.getUuid() + "");
                result.add(yearVo);
            }
        }
        return result;
    }

    @Override
    public List<CatVo> getCats(Integer id) {
        List<MoocCatDictT> moocCatDictTS = null;
        if (id == 99) {
            moocCatDictTS = this.moocCatDictTMapper.selectList(null);
        } else {
            EntityWrapper<MoocCatDictT> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("uuid", id);
            moocCatDictTS = this.moocCatDictTMapper.selectList(entityWrapper);
        }
        List<CatVo> result = null;
        if (CollectionUtils.isNotEmpty(moocCatDictTS)) {
            result = new ArrayList<>();
            for (MoocCatDictT moocCatDictT : moocCatDictTS) {
                CatVo catVo = new CatVo();
                catVo.setCatId(moocCatDictT.getUuid() + "");
                catVo.setCatName(moocCatDictT.getShowName());
                result.add(catVo);
            }
        }
        return result;
    }

    @Override
    public List<SourceVo> getSource(Integer id) {
        List<MoocSourceDictT> moocSourceDictTS = null;
        if (id == 99) {
            moocSourceDictTS = this.moocSourceDictTMapper.selectList(null);
        } else {
            EntityWrapper<MoocSourceDictT> entityWrapper = new EntityWrapper<>();
            entityWrapper.eq("uuid", id);
            moocSourceDictTS = this.moocSourceDictTMapper.selectList(entityWrapper);
        }
        List<SourceVo> result = null;
        if (CollectionUtils.isNotEmpty(moocSourceDictTS)) {
            result = new ArrayList<>();
            for (MoocSourceDictT moocSourceDictT : moocSourceDictTS) {
                SourceVo yearVo = new SourceVo();
                yearVo.setSourceName(moocSourceDictT.getShowName());
                yearVo.setSourceId(moocSourceDictT.getUuid() + "");
                result.add(yearVo);
            }
        }
        return result;
    }

    @Override
    public FilmVo getFilm(int showType, int sortId, int catId, int sourceId, int yearId, int page, int pageSize) {

        EntityWrapper<MoocFilmT> entityWrapper = new EntityWrapper<>();
        // 1-正在热映，2-即将上映，3-经典影片
        entityWrapper.eq("film_status", showType);
        if (catId != 99) {
            String value = "%#" + catId + "#%";
            entityWrapper.like("film_cats", value);
        }
        if (sourceId != 99) {
            entityWrapper.eq("film_source", sourceId);
        }
        if (yearId != 99) {
            entityWrapper.eq("film_date", yearId);
        }

        // 1-按热门搜索，2-按时间搜索，3-按评价搜索

        Page<MoocFilmT> filmTPage = null;
        switch (sortId) {
            case 1:
                filmTPage = new Page<>(page, pageSize, showType == 2 ? "film_preSaleNum" : "film_box_office");
                break;
            case 2:
                filmTPage = new Page<>(page, pageSize, "film_time");
                break;
            case 3:
                filmTPage = new Page<>(page, pageSize, showType == 2 ? "film_preSaleNum" : "film_score");
                break;
        }
        List<MoocFilmT> moocFilmTS = this.moocFilmTMapper.selectPage(filmTPage, entityWrapper);
        List<FilmInfo> films = getFilmInfos(moocFilmTS);
        Integer count = this.moocFilmTMapper.selectCount(entityWrapper);

        FilmVo filmVo = new FilmVo();
        filmVo.setFilms(films);
        filmVo.setNowPage(page);
        filmVo.setFilmsNum(pageSize);
        filmVo.setTotalPage((count / pageSize) + 1);

        return filmVo;
    }

    @Override
    public FilmDetailVo getFilm(String searchParam, int searchType) {
        FilmDetailVo detail = null;
        switch (searchType) {
            case 1:
                detail = this.moocFilmTMapper.getFilmDetailById(Integer.valueOf(searchParam));
                break;
            case 2:
                detail = this.moocFilmTMapper.getFilmDetailByName(searchParam);
                break;
        }
        return detail;
    }

    @Override
    public FilmDescVo getFilmDesc(String uuid) {

        FilmDescVo filmDescVo = new FilmDescVo();
        filmDescVo.setFilmId(uuid);
        MoocFilmInfoT moocFilmInfoT = new MoocFilmInfoT();
        moocFilmInfoT.setFilmId(uuid);
        moocFilmInfoT = this.moocFilmInfoTMapper.selectOne(moocFilmInfoT);
        filmDescVo.setBiography(moocFilmInfoT.getBiography());

        MoocActorT director = this.moocActorTMapper.selectById(moocFilmInfoT.getDirectorId());
        ActorVo actorVo = ActorVo.builder().directorName(director.getActorName()).imgAddress(director.getActorImg()).build();

        List<ActorVo> actor = this.moocActorTMapper.getActorByFilmId(uuid);
        ActorsVo actorsVo = new ActorsVo();
        actorsVo.setActors(actor);
        actorsVo.setDirector(actorVo);
        filmDescVo.setActors(actorsVo);

        String filmImgsStr = moocFilmInfoT.getFilmImgs();
        String[] filmImgs = filmImgsStr.split(",");
        ImgVo imgVo = new ImgVo();
        imgVo.setMainImg(filmImgs[0]);
        imgVo.setImg01(filmImgs[1]);
        imgVo.setImg02(filmImgs[2]);
        imgVo.setImg03(filmImgs[3]);
        imgVo.setImg04(filmImgs[4]);

        filmDescVo.setImgs(imgVo);
        return filmDescVo;
    }
}

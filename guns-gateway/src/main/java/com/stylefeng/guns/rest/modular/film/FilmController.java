package com.stylefeng.guns.rest.modular.film;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.model.*;
import com.stylefeng.guns.rest.modular.film.vo.FilmConditionVo;
import com.stylefeng.guns.rest.modular.film.vo.FilmIndexVo;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import com.stylefeng.guns.rest.utils.MeetingKit;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/film")
public class FilmController {

    private static String IMG_PRE = "img.meeting.cn";


    @Reference(interfaceClass = FilmServiceAPI.class)
    private FilmServiceAPI filmServiceAPI;

    @GetMapping("/getIndex")
    public ResponseVo getFilmIndex() {
        FilmIndexVo filmIndexVo = new FilmIndexVo();

        filmIndexVo.setBanners(filmServiceAPI.getBanners());
        filmIndexVo.setTop100(filmServiceAPI.getTop());
        filmIndexVo.setSoonFilms(filmServiceAPI.getSoonFilms(true, 10));
        filmIndexVo.setHotFilms(filmServiceAPI.getHotFilms(true, 10));
        filmIndexVo.setExpectRanking(filmServiceAPI.getExpectRanking());
        filmIndexVo.setBoxRanking(filmServiceAPI.getBoxRanking());

        return ResponseVo.ok(IMG_PRE, filmIndexVo);
    }

    @GetMapping("/getConditionList")
    public ResponseVo getFilmByCriteria(@RequestParam(value = "catId", required = false, defaultValue = "99") String catId,
                                        @RequestParam(value = "sourceId", required = false, defaultValue = "99") String sourceId,
                                        @RequestParam(value = "yearId", required = false, defaultValue = "99") String yearId) throws IllegalAccessException {
        List<CatVo> cats = this.filmServiceAPI.getCats(99);
        MeetingKit.generateActive(cats, catId);
        List<SourceVo> source = this.filmServiceAPI.getSource(99);
        MeetingKit.generateActive(source, sourceId);
        List<YearVo> years = this.filmServiceAPI.getYears(99);
        MeetingKit.generateActive(years, yearId);


        FilmConditionVo filmConditionVo = new FilmConditionVo();
        filmConditionVo.setCatInfo(cats);
        filmConditionVo.setYearInfo(years);
        filmConditionVo.setSourceInfo(source);
        return ResponseVo.ok(filmConditionVo);
    }




    @GetMapping("/getFilms")
    public ResponseVo getFilms(@RequestParam(value = "showType", required = false, defaultValue = "1") Integer showType,
                               @RequestParam(value = "sortId", required = false, defaultValue = "1") Integer sortId,
                               @RequestParam(value = "catId", required = false, defaultValue = "99") Integer catId,
                               @RequestParam(value = "sourceId", required = false, defaultValue = "99") Integer sourceId,
                               @RequestParam(value = "yearId", required = false, defaultValue = "99") Integer yearId,
                               @RequestParam(value = "nowPage", required = false, defaultValue = "1") Integer nowPage,
                               @RequestParam(value = "pageSize", required = false, defaultValue = "18") Integer pageSize) {

        FilmVo film = this.filmServiceAPI.getFilm(showType, sortId, catId, sourceId, yearId, nowPage, pageSize);

        return ResponseVo.ok(IMG_PRE, film.getFilms(), film.getNowPage(), film.getTotalPage());
    }

    @GetMapping("/films/{searchParam}")
    public ResponseVo getFilmById(@PathVariable("searchParam") String searchParam, @RequestParam("searchType") Integer searchType) {
        FilmDetailVo film = this.filmServiceAPI.getFilm(searchParam, searchType);
        FilmDescVo filmDesc = this.filmServiceAPI.getFilmDesc(film.getFilmId() + "");

        film.setInfo04(filmDesc);
        return ResponseVo.ok(IMG_PRE, film);
    }

}

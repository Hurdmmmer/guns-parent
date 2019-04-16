package com.stylefeng.guns.rest.common.persistence.dao;

import com.stylefeng.guns.api.cinema.model.FilmInfoVo;
import com.stylefeng.guns.rest.common.persistence.model.MoocHallFilmInfoT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MoocHallFilmInfoTMapperTest {

    @Autowired
    private MoocHallFilmInfoTMapper moocHallFilmInfoTMapper;

    @Test
    public void getFilms() {
//        List<FilmInfoVo> cinemaFilm = moocHallFilmInfoTMapper.findCinemaFilm(1);
//        System.out.println("cinemaFilm = " + cinemaFilm);
        FilmInfoVo filmById = this.moocHallFilmInfoTMapper.findFilmById(3);
        System.out.println("filmById = " + filmById);
    }


}
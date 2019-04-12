package com.stylefeng.guns.rest.modular;

import com.stylefeng.guns.api.film.model.FilmDescVo;
import com.stylefeng.guns.api.film.model.FilmDetailVo;
import com.stylefeng.guns.api.film.model.FilmInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DefaultFilmServiceImlTest {

    @Autowired
    private DefaultFilmServiceIml defaultFilmServiceIml;

    @Test
    public void getBoxRanking() {

        List<FilmInfo> boxRanking = defaultFilmServiceIml.getBoxRanking();
        System.out.println("boxRanking = " + boxRanking);
    }

    @Test
    public void getFilm() {
        FilmDetailVo film = defaultFilmServiceIml.getFilm("2", 1);
        System.out.println("film = " + film);
    }

    @Test
    public void getFilmDesc() {
        FilmDescVo filmDesc = defaultFilmServiceIml.getFilmDesc("2");
        System.out.println("filmDesc = " + filmDesc);
    }
}
package com.stylefeng.guns.rest.modular.cinema;

import com.alibaba.dubbo.config.annotation.Reference;
import com.stylefeng.guns.api.cinema.CinemaServiceApi;
import com.stylefeng.guns.api.cinema.model.*;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaConditionVo;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaDetailVo;
import com.stylefeng.guns.rest.modular.cinema.vo.CinemaRequestVo;
import com.stylefeng.guns.rest.modular.vo.ResponseVo;
import com.stylefeng.guns.rest.utils.MeetingKit;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cinema")
public class CinemaController {

    @Reference(interfaceClass = CinemaServiceApi.class)
    private CinemaServiceApi cinemaServiceApi;

    @GetMapping("/getCinemas")
    public ResponseVo getCinemas(CinemaRequestVo cinemaRequestVo) {
        List<CinemaVo> cinema = this.cinemaServiceApi.findCinema(cinemaRequestVo.getBrandId(), cinemaRequestVo.getDistrictId(), cinemaRequestVo.getHallType(),
                cinemaRequestVo.getNowPage(), cinemaRequestVo.getPageSize());
        Integer total = this.cinemaServiceApi.getCount(cinemaRequestVo.getBrandId(), cinemaRequestVo.getDistrictId(), cinemaRequestVo.getHallType());
        int totalPage = (total/cinemaRequestVo.getPageSize()) + 1;
        return ResponseVo.ok(null, cinema, cinemaRequestVo.getNowPage(), totalPage);
    }

    @GetMapping("/getCondition")
    public ResponseVo getCondition(@RequestParam(value = "brandId", required = false, defaultValue = "99") Integer brandId,
                                   @RequestParam(value = "hallType", required = false, defaultValue = "99")Integer hallType,
                                   @RequestParam(value = "areaId", required = false, defaultValue = "99")Integer areaId) throws IllegalAccessException {

        List<AreaVo> areas = this.cinemaServiceApi.getAreas();
        MeetingKit.generateActive(areas, areaId + "");
        List<BrandVo> brand = this.cinemaServiceApi.getBrand();
        MeetingKit.generateActive(brand, brandId + "");
        List<HallTypeVo> hallTypeVos = this.cinemaServiceApi.getHallType();
        MeetingKit.generateActive(hallTypeVos, hallType + "");

        CinemaConditionVo cinemaConditionVo = new CinemaConditionVo();
        cinemaConditionVo.setAreaList(areas);
        cinemaConditionVo.setBrandList(brand);
        cinemaConditionVo.setHalltypeList(hallTypeVos);

        return ResponseVo.ok(cinemaConditionVo);
    }

    @GetMapping("/getFields")
    public ResponseVo getFields(Integer cinemaId) {
        List<FilmInfoVo> cinemaFilm = this.cinemaServiceApi.getCinemaFilm(cinemaId);
        CinemaInfoVo cinemaInfo = this.cinemaServiceApi.getCinemaInfo(cinemaId);
        CinemaDetailVo cinemaDetailVo = new CinemaDetailVo();
        cinemaDetailVo.setFilmList(cinemaFilm);
        cinemaDetailVo.setCinemaInfo(cinemaInfo);
        return ResponseVo.ok(cinemaDetailVo);
    }

    @PostMapping("/getFieldInfo")
    public ResponseVo getFieldInfo(@RequestParam("cinemaId") Integer cinemaId,
                                   @RequestParam("fieldId") Integer fieldId) {

        CinemaInfoVo cinemaInfo = this.cinemaServiceApi.getCinemaInfo(cinemaId);
        HallInfoVo hallInfo = this.cinemaServiceApi.getHallInfo(cinemaId, fieldId);
        FilmInfoVo filmInfoVo = this.cinemaServiceApi.getFilmBy(cinemaId, fieldId);
        Map<String, Object> map = new HashMap<>();
        map.put("filmInfo", filmInfoVo);
        map.put("cinemaInfo", cinemaInfo);
        map.put("hallInfo", hallInfo);
        return ResponseVo.ok("http://img.meetingshop.cn/", map);
    }

}

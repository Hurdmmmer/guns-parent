package com.stylefeng.guns.rest.modular;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;
import com.stylefeng.guns.api.cinema.CinemaServiceApi;
import com.stylefeng.guns.api.cinema.model.CinemaInfoVo;
import com.stylefeng.guns.api.cinema.model.FilmFieldVo;
import com.stylefeng.guns.api.cinema.model.FilmInfoVo;
import com.stylefeng.guns.api.cinema.model.HallInfoVo;
import com.stylefeng.guns.api.film.FilmServiceAPI;
import com.stylefeng.guns.api.film.model.FilmInfo;
import com.stylefeng.guns.api.order.OrderServiceApi;
import com.stylefeng.guns.api.order.model.OrderVo;
import com.stylefeng.guns.core.support.CollectionKit;
import com.stylefeng.guns.rest.common.persistence.dao.MoocOrderTMapper;
import com.stylefeng.guns.rest.common.persistence.model.MoocOrderT;
import com.stylefeng.guns.rest.utils.FTPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Slf4j
@Service(interfaceClass = OrderServiceApi.class)
public class DefaultOrderServiceImp implements OrderServiceApi {

    @Reference(interfaceClass = FilmServiceAPI.class)
    private FilmServiceAPI filmServiceAPI;

    @Reference(interfaceClass = CinemaServiceApi.class)
    private CinemaServiceApi cinemaServiceApi;

    @Autowired
    private MoocOrderTMapper orderTMapper;
    @Autowired
    private FTPUtils ftpUtils;

    @Override
    public boolean isTrueSeats(String filedId, String seats) {
        if (StringUtils.isEmpty(filedId) || StringUtils.isEmpty(seats)) {
            return false;
        }
        String fieldSeatsPath = this.orderTMapper.getFieldSeatsPath(Integer.valueOf(filedId));
        String seatsStr = this.ftpUtils.getSeats(fieldSeatsPath);
        seatsStr = JSONObject.parseObject(seatsStr).getString("ids");
        String[] seatsArr = seats.split(",");
        return Arrays.stream(seatsArr).allMatch(seatsStr::contains);
    }

    @Override
    public boolean isNotSoldSeats(String filedId, String seats) {
        List<MoocOrderT> moocOrderTS = this.orderTMapper.getSoldSeatsIds(Integer.valueOf(filedId));
        String[] seatsIds = seats.split(",");
        String ids = moocOrderTS.stream().map(MoocOrderT::getSeatsIds).collect(Collectors.joining(","));
        boolean result = Arrays.stream(seatsIds).anyMatch(ids::contains);
        return !result;
    }

    @Override
    public OrderVo saveOrder(String filedId, String seats, String seatsName, Integer userId) {

        FilmInfoVo filmInfoVo = this.cinemaServiceApi.getFilmBy(Integer.valueOf(filedId));
        HallInfoVo hallInfo = this.cinemaServiceApi.getHallInfo(Integer.valueOf(filedId));
        String price = hallInfo.getPrice();
        double totalPrice = getTotalPrice(seats.split(",").length, price);

        MoocOrderT moocOrderT = MoocOrderT.builder()
                .uuid(UUID.randomUUID().toString().replace("-", ""))
                .seatsName(seatsName)
                .seatsIds(seats)
                .orderUser(userId)
                .orderPrice(totalPrice)
                .filmPrice(Double.valueOf(price))
                .filmId(filmInfoVo.getFilmId())
                .fieldId(Integer.valueOf(filedId))
                .cinemaId(hallInfo.getCinemaId())
                .build();
        Integer result = this.orderTMapper.insert(moocOrderT);
        if (result >0) {
            return moocOrderToVo(moocOrderT);
        }
        return null;
    }

    private OrderVo moocOrderToVo(MoocOrderT moocOrderT) {

        return null;
    }

    private double getTotalPrice(int total, String price) {
        BigDecimal totalB = new BigDecimal(total);
        BigDecimal priceB = new BigDecimal(price);
        // 四舍五入
        return totalB.multiply(priceB).setScale(2, BigDecimal.ROUND_HALF_DOWN).doubleValue();
    }

    @Override
    public List<OrderVo> getOrderByUserId(Integer userId) {
        List<MoocOrderT> moocOrderTS = this.orderTMapper.selectByOrderUser(userId);
        if (CollectionUtils.isEmpty(moocOrderTS)) {
            return null;
        }
        List<OrderVo> orderVos = CollectionKit.newArrayList();
        for (MoocOrderT moocOrderT : moocOrderTS) {
            OrderVo orderVo = new OrderVo();
            orderVo.setSeatsName(moocOrderT.getSeatsName());
            orderVo.setOrderTimestamp(moocOrderT.getOrderTime().getTime());
            orderVo.setOrderStatus(moocOrderT.getOrderStatus() + "");
            orderVo.setOrderPrice(moocOrderT.getOrderPrice() + "");
            orderVo.setOrderId(moocOrderT.getUuid() + "");
            FilmInfo filmInfo = null;
            FilmFieldVo filmFieldVo = null;
            try {
                filmInfo = filmServiceAPI.getFilmInfo(moocOrderT.getFilmId());
                filmFieldVo = orderTMapper.getFieldInfo(moocOrderT.getFieldId());
                CinemaInfoVo cinemaInfo = this.cinemaServiceApi.getCinemaInfo(moocOrderT.getCinemaId());
                orderVo.setFilmName(null != filmInfo ? filmInfo.getFilmName() : null);
                assert filmInfo != null;
                orderVo.setFieldTime(filmInfo.getShowTime() + " " + filmFieldVo.getBeginTime());
                orderVo.setCinemaName(cinemaInfo.getCinemaName());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            orderVos.add(orderVo);
        }
        return orderVos;
    }

    @Override
    public String getSoldSeatsByFiledId(String filedId) {
        List<MoocOrderT> soldSeatsIds = this.orderTMapper.getSoldSeatsIds(Integer.valueOf(filedId));
        return soldSeatsIds.stream().map(MoocOrderT::getSeatsIds).collect(Collectors.joining(","));
    }
}

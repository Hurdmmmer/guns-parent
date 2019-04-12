package com.stylefeng.guns.api.film.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ActorsVo implements Serializable {
    private List<ActorVo> actors;
    private ActorVo director;
}

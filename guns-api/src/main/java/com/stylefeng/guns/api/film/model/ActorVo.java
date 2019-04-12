package com.stylefeng.guns.api.film.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@Builder
public class ActorVo implements Serializable {
    private String imgAddress;
    private String directorName;
    private String roleName;
}

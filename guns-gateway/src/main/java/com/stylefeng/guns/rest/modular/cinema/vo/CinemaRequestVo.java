package com.stylefeng.guns.rest.modular.cinema.vo;

import lombok.Data;

@Data
public class CinemaRequestVo {
    /** 影院编号 */
    private String brandId = "99";
    /** 影厅类型 */
    private String hallType = "99";
    /** 行政区编号 */
    private String districtId = "99";
    private Integer pageSize = 12;
    private Integer nowPage = 1;
}

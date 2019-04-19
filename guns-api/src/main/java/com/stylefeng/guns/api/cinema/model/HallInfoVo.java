package com.stylefeng.guns.api.cinema.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class HallInfoVo implements Serializable {
    private Integer hallFieldId;
    private String hallName;
    private Integer cinemaId;
    private String price;
    /** 影厅座位排列  */
    private String seatFile;
    /** 销售的座位编号 */
    private String soldSeats;

}

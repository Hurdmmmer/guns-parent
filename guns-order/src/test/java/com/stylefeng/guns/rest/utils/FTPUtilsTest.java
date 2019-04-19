package com.stylefeng.guns.rest.utils;

import com.stylefeng.guns.rest.GunsOrderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@SpringBootTest(classes = GunsOrderApplication.class)
@RunWith(SpringRunner.class)
public class FTPUtilsTest {
    @Autowired
    private FTPUtils ftpUtils;

    @Test
    public void getSeats() {
        String seats = ftpUtils.getSeats("/seats/cgs.json");
        System.out.println("seats = " + seats);
    }
}
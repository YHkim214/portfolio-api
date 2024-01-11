package com.yoonho.holostats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HoloStatsApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoloStatsApplication.class, args);
    }

}

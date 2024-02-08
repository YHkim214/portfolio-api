package com.yoonho.holoboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class HoloBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(HoloBoardApplication.class, args);
    }

}

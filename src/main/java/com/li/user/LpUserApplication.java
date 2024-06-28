package com.li.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LpUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(LpUserApplication.class, args);
        System.out.println("---电力负荷预测系统---");
    }

}

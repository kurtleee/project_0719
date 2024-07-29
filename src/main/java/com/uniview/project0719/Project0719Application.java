package com.uniview.project0719;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.uniview.project0719.mapper")
public class Project0719Application {

    public static void main(String[] args) {
        SpringApplication.run(Project0719Application.class, args);
    }

}

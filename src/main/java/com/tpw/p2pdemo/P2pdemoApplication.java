package com.tpw.p2pdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tpw.p2pdemo.mapper")
@SpringBootApplication
public class P2pdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(P2pdemoApplication.class, args);
    }

}

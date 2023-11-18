package com.bs.searchapi;

import com.bs.kakao.feign.KakaoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = KakaoClient.class)
@SpringBootApplication
public class BsSearchApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(BsSearchApiApplication.class, args);
    }
}

package com.bs.external;

import com.bs.kakao.feign.KakaoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = KakaoClient.class)
@SpringBootApplication
public class BsExternalApplication {
    public static void main(String[] args) {
        SpringApplication.run(BsExternalApplication.class, args);
    }
}

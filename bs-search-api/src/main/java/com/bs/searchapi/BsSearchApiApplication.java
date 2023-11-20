package com.bs.searchapi;

import com.bs.common.LocalDateTimeDeserializer;
import com.bs.kakao.feign.KakaoClient;
import com.bs.naver.feign.NaverClient;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDateTime;

@EnableFeignClients(clients = {KakaoClient.class, NaverClient.class})
@SpringBootApplication
public class BsSearchApiApplication implements Jackson2ObjectMapperBuilderCustomizer {
    public static void main(String[] args) {
        SpringApplication.run(BsSearchApiApplication.class, args);
    }

    @Override
    public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
        jacksonObjectMapperBuilder.modulesToInstall(
                        new JavaTimeModule().addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer()))
                .build();
    }
}

package com.songareeit.fcmpush.config;

import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OpenFeignConfig {

    /**
     * 1초의 간격으로 최대5번 재시도
     * 이때 시도할 때마다 간격은 증가하는데, 최소 3초까지 증가
     *
     * @return : Retryer
     */
    @Bean
    Retryer.Default retryer() {
        return new Retryer.Default(
                1000L,
                TimeUnit.SECONDS.toMillis(3L),
                5);
    }
}
package com.songareeit.fcmpush;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@EnableFeignClients
@SpringBootApplication
public class FcmpushApplication {

    public static void main(String[] args) {
        SpringApplication.run(FcmpushApplication.class, args);
    }
}

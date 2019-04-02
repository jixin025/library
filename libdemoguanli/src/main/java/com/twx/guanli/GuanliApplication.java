package com.twx.guanli;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GuanliApplication {
    public static void main(String age[]){
        SpringApplication.run(GuanliApplication.class,age);
    }
}

package com.twx.jiehuan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JiehuanApplication {
    public static void main(String[]arg){
        SpringApplication.run(JiehuanApplication.class,arg);
    }
}

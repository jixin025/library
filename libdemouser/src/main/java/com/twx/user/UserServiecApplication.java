package com.twx.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServiecApplication {
    public static void main(String []ages){
        SpringApplication.run(UserServiecApplication.class,ages);
    }
}

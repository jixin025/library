package com.twx.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class BookServiceApplication {
    public static void main(String []age){
        SpringApplication.run(BookServiceApplication.class,age);
    }
}

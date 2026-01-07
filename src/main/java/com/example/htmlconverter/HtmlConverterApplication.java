package com.example.htmlconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HtmlConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HtmlConverterApplication.class, args);
    }
}

package com.example.telegram_one;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TelegramOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramOneApplication.class, args);
    }

}

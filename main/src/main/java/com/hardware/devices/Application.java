package com.hardware.devices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hardware")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

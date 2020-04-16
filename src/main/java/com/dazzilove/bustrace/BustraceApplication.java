package com.dazzilove.bustrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
public class BustraceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BustraceApplication.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

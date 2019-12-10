package com.dazzilove.bustrace.config;

import com.dazzilove.bustrace.service.BusRouteClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class BustraceConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.dazzilove.bustrace.service.wsdl");
        return marshaller;
    }

}

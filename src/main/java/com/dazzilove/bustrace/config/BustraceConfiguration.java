package com.dazzilove.bustrace.config;

import com.dazzilove.bustrace.service.BusRouterClient;
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

    @Bean
    public BusRouterClient busRouterClient(Jaxb2Marshaller marshaller) {
        BusRouterClient client = new BusRouterClient();
        client.setDefaultUri("http://openapi.gbis.go.kr/ws/busrouteservice");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}

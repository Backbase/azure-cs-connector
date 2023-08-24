package com.backbase.communication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
//@ImportAutoConfiguration(value = {TrackingService.class, MessageSenderService.class, StreamSender.class}) //TODO should not be needed after ssdk-16 version of communication-channel library
public class AzureCSConnectorApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(AzureCSConnectorApplication.class, args);
    }

}
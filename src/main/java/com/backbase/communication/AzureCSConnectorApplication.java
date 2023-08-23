package com.backbase.communication;

import com.backbase.buildingblocks.commns.service.MessageSenderService;
import com.backbase.buildingblocks.commns.service.StreamSender;
import com.backbase.buildingblocks.commns.service.TrackingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@ImportAutoConfiguration(value = {TrackingService.class, MessageSenderService.class, StreamSender.class})
public class AzureCSConnectorApplication extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(AzureCSConnectorApplication.class, args);
    }

}
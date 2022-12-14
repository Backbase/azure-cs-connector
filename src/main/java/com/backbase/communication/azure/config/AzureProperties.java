package com.backbase.communication.azure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotBlank;

@Configuration
@ConfigurationProperties("azure")
@Data
public class AzureProperties {
    @NotBlank(message = "'emailConnectionString' cannot be empty/null")
    private String emailConnectionString;
}

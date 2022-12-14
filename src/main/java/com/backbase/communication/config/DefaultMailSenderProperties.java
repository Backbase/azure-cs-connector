package com.backbase.communication.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
@Configuration
@ConfigurationProperties(prefix = "backbase.mail")
@Getter
@Setter
@Validated
public class DefaultMailSenderProperties {

    private String fromName;
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",
        message = "'fromAddress' should have email format!")
    private String fromAddress;

}

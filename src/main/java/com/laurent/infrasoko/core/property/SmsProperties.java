package com.laurent.infrasoko.core.property;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sms")
@Slf4j
public class SmsProperties {
    private String accountSid;
    private String authToken;
    private String fromNumber;
}

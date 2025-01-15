package com.laurent.infrasoko.core.property;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Slf4j
public class MailProperties {
    private String host;
    private String port;
    private String username;
    private String password;
}

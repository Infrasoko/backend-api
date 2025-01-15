package com.laurent.infrasoko.core.property;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "swagger-doc")
@Slf4j
public class SwaggerProperties {
    private String serverUrl;
    private String serverDescription;
    private String infoTitle;
    private String infoDescription;
    private String infoVersion;
    private String infoContactName;
    private String infoContactEmail;
}

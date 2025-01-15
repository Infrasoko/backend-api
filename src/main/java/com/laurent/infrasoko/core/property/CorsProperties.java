package com.laurent.infrasoko.core.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "infrasoko.cors")
public class CorsProperties {
    private String allowOrigins;

    public String[] getAllowOrigins() {
        if (allowOrigins.contains(","))
            return allowOrigins.trim().split("\\s*,\\s*");
        else
            return new String[]{allowOrigins.trim()};
    }
}


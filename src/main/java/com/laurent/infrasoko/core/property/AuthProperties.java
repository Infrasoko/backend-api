package com.laurent.infrasoko.core.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "infrasoko.auth")
@Slf4j
public class AuthProperties {
    private JwtProperties jwt;
    private String publicApiPaths;
    private AccountVerificationProperties accountVerification;

    public String[] getPublicApiPaths() {
        if (isValidPathString(publicApiPaths)) {
            return publicApiPaths.trim().split("\\s*,\\s*");
        } else {
            log.warn("Invalid characters found in publicApiPaths: {}", publicApiPaths);
            return new String[0];
        }
    }

    /**
     * Checks if a provided path string contains only valid characters.
     * Valid characters currently include alphanumeric characters (a-z, A-Z, 0-9),
     * slashes (/), space ( )and asterisks (*).
     *
     * @param pathString The path string to validate.
     * @return true if the path string contains only valid characters, false
     *         otherwise.
     */
    private boolean isValidPathString(String pathString) {

        if (!InfrasokoProperties.isValidProperty(pathString))
            return false;
        Pattern pattern = Pattern.compile("[^0-9a-zA-Z,/ *]+"); // Adjust this pattern as needed
        Matcher matcher = pattern.matcher(pathString);
        return !matcher.matches();
    }

    @Getter
    @Setter
    public static class JwtProperties {
        @NotBlank
        private String secret;
        @NotBlank
        private int accessTokenLifespan;
        @NotBlank
        private int refreshTokenLifespan;
    }

    @Setter
    @Getter
    public static class AccountVerificationProperties {
        @NotNull
        private int codeExpirationPeriod;
        @NotBlank
        private String verificationLink;
        @NotBlank
        private String loginLink;
    }
}
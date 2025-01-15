package com.laurent.infrasoko.core.property;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "infrasoko.file")
@Setter
public class FileUploadProperties {
    @NotBlank(message = "Storage path cannot be blank")
    private String storagePath;

    @Positive(message = "Max file size must be a positive value")
    private int maxFileSizeInMb;

    /**
     * Gets the maximum file size in bytes.
     * Converts the size from megabytes to bytes.
     *
     * @return the max file size in bytes.
     */
    public long getMaxFileSizeInBytes() {
        return maxFileSizeInMb * 1024L * 1024L;
    }

    public String getStoragePath() {
        return this.storagePath;
    }
}

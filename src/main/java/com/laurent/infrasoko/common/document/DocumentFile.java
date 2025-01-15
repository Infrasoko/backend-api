package com.laurent.infrasoko.common.document;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.laurent.infrasoko.common.model.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "document_files")
public class DocumentFile extends BaseModel {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "description")
    private String description;

    @Column(name = "mime_type", nullable = false)
    private String mimeType;

    @Column(name = "extension", nullable = false)
    private String extension;

    @Column(name = "size_in_bytes", nullable = false)
    private Long sizeInBytes;

    @Column(name = "uploader_id")
    private Long uploaderId;

    @Column(name = "checksum")
    private String checksum;

    @Column(name = "is_encrypted", nullable = false, columnDefinition = "boolean default false")
    private boolean encrypted;

    public String getExtension() {
        return extension;
    }
}
package com.laurent.infrasoko.common.document;

import com.laurent.infrasoko.common.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;

@RestController
@RequestMapping("/api/v1/documents")
@Tag(name = "Document Management")
public class DocumentFileController {
    private static final Logger log = LoggerFactory.getLogger(DocumentFileController.class);
    private final DocumentFileService documentFileService;

    public DocumentFileController(DocumentFileService documentFileService) {
        this.documentFileService = documentFileService;
    }

    @GetMapping("/{documentFileId}/download")
    @Operation(summary = "Download an uploaded document")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long documentFileId) {
        DocumentFile documentFile = documentFileService.findById(documentFileId)
                .orElseThrow(() -> new NotFoundException("The file with ID " + documentFileId + " does not exist."));

        try {
            // Build the file path
            Path filePath = documentFileService.getFilePath(documentFile);

            // Load file as a resource
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                // Set the content type based on the file's MIME type
                String contentType = documentFile.getMimeType();
                String filename = documentFile.getId() + "_" + documentFile.getFileName();

                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "attachment; filename=\"" + filename + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS,
                                HttpHeaders.CONTENT_DISPOSITION,
                                HttpHeaders.CONTENT_LENGTH, HttpHeaders.CONTENT_TYPE)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (Exception e) {
            log.error("Error downloading uploaded document: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
}

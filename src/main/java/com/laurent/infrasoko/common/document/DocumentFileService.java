package com.laurent.infrasoko.common.document;

import com.laurent.infrasoko.common.exception.FileStorageException;
import com.laurent.infrasoko.common.exception.InputValidationException;
import com.laurent.infrasoko.core.property.FileUploadProperties;
import io.hypersistence.tsid.TSID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class DocumentFileService {

    private final DocumentFileRepository documentFileRepository;
    private final Path fileStorageLocation;

    // Define accepted file types and maximum file size
    private static final List<String> ALLOWED_FILE_TYPES = Arrays.asList("application/pdf", "image/jpeg", "image/png");
    private final long maxFileSize;

    public DocumentFileService(DocumentFileRepository documentFileRepository,
                               FileUploadProperties fileUploadProperties) {
        this.documentFileRepository = documentFileRepository;
        this.maxFileSize = fileUploadProperties.getMaxFileSizeInBytes();
        this.fileStorageLocation = Paths.get(fileUploadProperties.getStoragePath()).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            log.error("Could not create the directory where uploaded files will be stored.", ex);
            throw new FileStorageException("Could not create the directory where uploaded files will be stored.");
        }
    }

    public String generateChecksum(File file) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] byteArray = new byte[1024];
            int bytesCount;
            while ((bytesCount = fis.read(byteArray)) != -1) {
                digest.update(byteArray, 0, bytesCount);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : digest.digest()) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString(); // Return checksum as hex string
    }

    public Path getFilePath(DocumentFile documentFile) {
        String filename = documentFile.getId() + documentFile.getExtension();
        return this.fileStorageLocation.resolve(filename);
    }

    public DocumentFile saveFile(MultipartFile file, String fileName, String fileDescription, Long uploaderId) {
        if (file == null || file.isEmpty()) {
            return null;
        }

        if (fileName == null || fileName.isEmpty()) {
            fileName = (file.getOriginalFilename() != null ? file.getOriginalFilename() : "No Name");
        }
        ;
        try {
            // Validate file type and size
            validateFile(file);

            Long documentFileId = TSID.Factory.getTsid().toLong();
            String fileExtension = getFileExtension(fileName);
            String uniqueFileName = documentFileId + fileExtension;
            Path destinationPath = this.fileStorageLocation.resolve(uniqueFileName);

            File destinationFile = destinationPath.toFile();
            file.transferTo(destinationFile);

            String checksum = generateChecksum(destinationFile);
            long sizeInBytes = file.getSize();

            DocumentFile documentFile = new DocumentFile();
            documentFile.setId(documentFileId);
            documentFile.setFileName(fileName);
            documentFile.setDescription(fileDescription);
            documentFile.setMimeType(file.getContentType());
            documentFile.setExtension(fileExtension);
            documentFile.setSizeInBytes(sizeInBytes);
            documentFile.setUploaderId(uploaderId);
            documentFile.setChecksum(checksum);
            documentFile.setEncrypted(false); // Set to true if encryption is applied

            return documentFileRepository.save(documentFile);
        } catch (IOException ex) {
            log.error("Failed to store file: {}", fileName, ex);
            throw new FileStorageException("Failed to store file " + fileName);
        } catch (NoSuchAlgorithmException e) {
            log.error("Error generating checksum for file: {}", fileName, e);
            throw new FileStorageException("Error generating checksum - " + e.getMessage());
        }
    }

    public DocumentFile saveFile(MultipartFile file, Long uploaderAmisId) {
        return saveFile(file, null, null, uploaderAmisId);
    }

    public void validateFile(MultipartFile file) {
        // Validate file type
        if (!ALLOWED_FILE_TYPES.contains(file.getContentType())) {
            throw new FileStorageException("Invalid file type: " + file.getContentType());
        }

        // Validate file size
        if (file.getSize() > maxFileSize) {
            throw new FileStorageException("File size exceeds the maximum limit of " + (maxFileSize / (1024 * 1024)) + " MB");
        }

        log.info("File validated: {} (Size: {} bytes)", file.getOriginalFilename(), file.getSize());
    }

    public Optional<DocumentFile> findById(Long id) {
        if (id == null) {
            throw new InputValidationException("Document Id cannot be null");
        }
        return documentFileRepository.findById(id);
    }

    public List<DocumentFile> getByIds(List<Long> ids) {
        return documentFileRepository.findByIdIn(new HashSet<>(ids));
    }

    public Page<DocumentFile> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by("createdAt").descending());
        return documentFileRepository.findAll(pageable);
    }

    public byte[] getFileContent(Long documentId) {
        if (documentId == null || !documentFileRepository.existsById(documentId)) {
            throw new FileStorageException("File not found with id " + documentId);
        }

        Path filePath = fileStorageLocation.resolve(documentId.toString()).normalize();

        try {
            return Files.readAllBytes(filePath);
        } catch (IOException ex) {
            log.error("Could not read file: {}", documentId, ex);
            throw new FileStorageException("Could not read file: " + documentId);
        }
    }

    public void deleteFile(Long documentId) {
        if (documentId == null || !documentFileRepository.existsById(documentId)) {
            throw new FileStorageException("File not found with id " + documentId);
        }

        Path filePath = fileStorageLocation.resolve(documentId.toString()).normalize();

        try {
            Files.deleteIfExists(filePath);
            documentFileRepository.deleteById(documentId);
        } catch (IOException ex) {
            log.error("Could not delete file: {}", documentId, ex);
            throw new FileStorageException("Could not delete file: " + documentId);
        }
    }

    private String getFileExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        return lastIndexOfDot > 0 ? fileName.substring(lastIndexOfDot) : ""; // Return .extension or empty
    }
    public List<DocumentFile> saveFiles(List<MultipartFile> files, Long uploaderAmisId) {
        return files.stream()
                .map(file -> saveFile(file, uploaderAmisId)) // Save each file
                .collect(Collectors.toList());
    }
}

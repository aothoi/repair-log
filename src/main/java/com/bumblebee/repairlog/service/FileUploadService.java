package com.bumblebee.repairlog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import static com.bumblebee.repairlog.util.Paths.IMAGE_UPLOAD_PATH;
import static com.bumblebee.repairlog.util.Paths.TEST_REPORT_UPLOAD_PATH;

/**
 * @author aothoi
 * @since 09/02/2025
 */
@Service
@RequiredArgsConstructor
public class FileUploadService {

    public String uploadFile(MultipartFile file, String fileBaseName, boolean isTestReport) {
        try {
            String extension = getFileExtension(Objects.requireNonNull(file.getOriginalFilename()));
            String newFileName = fileBaseName + extension;

            Path uploadPath = Paths.get("").toAbsolutePath().resolve(isTestReport ? TEST_REPORT_UPLOAD_PATH : IMAGE_UPLOAD_PATH);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            byte[] bytes = file.getBytes();
            Path path = uploadPath.resolve(newFileName);
            Files.write(path, bytes);

            return newFileName;

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return null;
    }

    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        return (lastIndex == -1) ? "" : fileName.substring(lastIndex);
    }
}

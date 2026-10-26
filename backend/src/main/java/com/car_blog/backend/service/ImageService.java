package com.car_blog.backend.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private static final Logger log = LoggerFactory.getLogger(ImageService.class);

    private final Path uploadsPath = Path.of("uploads");

    public void deleteImage(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank()) return;

        try {
            Path imagePath = uploadsPath.resolve(Path.of(imageUrl).getFileName());
            Files.deleteIfExists(imagePath);
        } catch (IOException e) {
            log.warn("Could not delete image file {}", imageUrl, e);
        }
    }
}
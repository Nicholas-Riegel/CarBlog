package com.car_blog.backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final Path uploadsPath = Path.of("uploads");

    @PostMapping
    public ResponseEntity<Map<String, String>> upload(
        @RequestParam("imageFile") MultipartFile file) throws IOException {
        
        // The browser sets the file type automatically when the user picks a file. When the user chooses a file in <input type="file">, the browser creates a File object. It fills in a type property (the MIME type) by looking at the file's extension (for example .jpg becomes image/jpeg, .png becomes image/png) using the operating system's or browser's built-in mapping.
        if (file.isEmpty() 
            || file.getContentType() == null 
            || !file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().build();
        }

        Files.createDirectories(uploadsPath);

        String originalName = file.getOriginalFilename();

        String extension = (originalName != null && originalName.contains("."))
                ? originalName.substring(originalName.lastIndexOf('.'))
                : "";
        
        String filename = UUID.randomUUID() + extension;

        file.transferTo(uploadsPath.resolve(filename));

        String path = "/uploads/" + filename;

        return ResponseEntity.ok(Map.of("imageUrl", path));
    }
}
package com.example.htmlconverter.controller;

import com.example.htmlconverter.model.ConversionTask;
import com.example.htmlconverter.model.User;
import com.example.htmlconverter.repository.TaskRepository;
import com.example.htmlconverter.repository.UserRepository;
import com.example.htmlconverter.service.ConversionService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ConversionController {

    private final ConversionService conversionService;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public ConversionController(ConversionService conversionService, TaskRepository taskRepository, UserRepository userRepository) {
        this.conversionService = conversionService;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/convert")
    public ResponseEntity<?> convertFile(@RequestParam("file") MultipartFile file,
                                         @RequestParam("format") String format,
                                         Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        ConversionTask task = conversionService.createTask(file, format, user);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/tasks")
    public List<ConversionTask> getUserTasks(Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        return taskRepository.findByUserOrderByCreatedAtDesc(user);
    }

    @GetMapping("/download/{fileName:.+}")
    @SuppressWarnings("null")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) {
        try {
            Path filePath = conversionService.getResultPath(fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

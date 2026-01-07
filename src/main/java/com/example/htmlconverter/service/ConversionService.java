package com.example.htmlconverter.service;

import com.example.htmlconverter.converter.MarkdownConverter;
import com.example.htmlconverter.converter.WordConverter;
import com.example.htmlconverter.core.DocumentConverter;
import com.example.htmlconverter.model.ConversionTask;
import com.example.htmlconverter.repository.TaskRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ConversionService {

    private final TaskRepository taskRepository;
    private final Path fileStorageLocation;

    public ConversionService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.fileStorageLocation = Paths.get("data").toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation.resolve("uploads"));
            Files.createDirectories(this.fileStorageLocation.resolve("results"));
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public ConversionTask createTask(MultipartFile file, String targetFormat, com.example.htmlconverter.model.User user) {
        String fileName = file.getOriginalFilename();
        ConversionTask task = new ConversionTask(fileName, targetFormat, user);
        task = taskRepository.save(task);
        
        // Save file
        try {
            String savedFileName = task.getId() + "_" + UUID.randomUUID() + "_" + fileName;
            Path targetLocation = this.fileStorageLocation.resolve("uploads").resolve(savedFileName);
            Files.copy(file.getInputStream(), targetLocation);
            
            // Process async
            processConversion(task.getId(), targetLocation.toFile(), targetFormat);
            
        } catch (IOException ex) {
            task.setStatus("FAILED");
            task.setErrorMessage("Could not store file " + fileName + ". Please try again!");
            taskRepository.save(task);
        }
        
        return task;
    }

    @Async
    public void processConversion(long taskId, File inputFile, String targetFormat) {
        ConversionTask task = taskRepository.findById(taskId).orElse(null);
        if (task == null) return;

        int maxRetries = 3;
        int attempt = 0;
        Exception lastException = null;

        while (attempt < maxRetries) {
            try {
                if (attempt == 0) {
                    task.setStatus("PROCESSING");
                } else {
                    task.setStatus("RETRYING (" + (attempt + 1) + "/" + maxRetries + ")");
                }
                taskRepository.save(task);

                DocumentConverter converter;
                String outputExtension;
                
                switch (targetFormat.toLowerCase()) {
                    case "markdown":
                    case "md":
                        converter = new MarkdownConverter();
                        outputExtension = ".md";
                        break;
                    case "word":
                    case "docx":
                        converter = new WordConverter();
                        outputExtension = ".docx";
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported format: " + targetFormat);
                }

                String outputFileName = "result_" + task.getId() + outputExtension;
                File outputFile = this.fileStorageLocation.resolve("results").resolve(outputFileName).toFile();

                converter.convert(inputFile, outputFile);

                task.setStatus("COMPLETED");
                task.setResultPath(outputFileName); // Store relative path or filename
                taskRepository.save(task);
                return; // Success, exit method

            } catch (Exception e) {
                lastException = e;
                attempt++;
                System.err.println("Conversion attempt " + attempt + " failed for task " + taskId + ": " + e.getMessage());
                // Optional: add small delay before retry
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
            }
        }

        // If we reach here, all retries failed
        task.setStatus("FAILED");
        task.setErrorMessage("Failed after " + maxRetries + " attempts. Last error: " + (lastException != null ? lastException.getMessage() : "Unknown error"));
        taskRepository.save(task);
        if (lastException != null) {
            lastException.printStackTrace();
        }
    }

    public Path getResultPath(String fileName) {
        return this.fileStorageLocation.resolve("results").resolve(fileName).normalize();
    }
}

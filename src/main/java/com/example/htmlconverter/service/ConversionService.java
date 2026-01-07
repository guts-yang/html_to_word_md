package com.example.htmlconverter.service;

import com.example.htmlconverter.converter.MarkdownConverter;
import com.example.htmlconverter.converter.PdfConverter;
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
    public void processConversion(Long taskId, File inputFile, String targetFormat) {
        ConversionTask task = taskRepository.findById(taskId).orElse(null);
        if (task == null) return;

        try {
            task.setStatus("PROCESSING");
            taskRepository.save(task);

            DocumentConverter converter;
            String outputExtension;
            
            switch (targetFormat.toLowerCase()) {
                case "pdf":
                    converter = new PdfConverter();
                    outputExtension = ".pdf";
                    break;
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

        } catch (Exception e) {
            task.setStatus("FAILED");
            task.setErrorMessage(e.getMessage());
            taskRepository.save(task);
            e.printStackTrace();
        }
    }

    public Path getResultPath(String fileName) {
        return this.fileStorageLocation.resolve("results").resolve(fileName).normalize();
    }
}

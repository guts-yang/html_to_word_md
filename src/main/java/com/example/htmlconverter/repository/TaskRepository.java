package com.example.htmlconverter.repository;

import com.example.htmlconverter.model.ConversionTask;
import com.example.htmlconverter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<ConversionTask, Long> {
    List<ConversionTask> findByUserOrderByCreatedAtDesc(User user);
}

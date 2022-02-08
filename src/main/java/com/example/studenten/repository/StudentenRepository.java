package com.example.studenten.repository;

import com.example.studenten.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentenRepository  extends JpaRepository<Student, Long> {
}

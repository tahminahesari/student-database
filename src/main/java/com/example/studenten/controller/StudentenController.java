package com.example.studenten.controller;

import com.example.studenten.model.Student;
import com.example.studenten.repository.StudentenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentenController {

    @Autowired
    StudentenRepository repos;

    @GetMapping("/studenten")
    public ResponseEntity<Object> getTeachers() {
        List<Student> ld = repos.findAll();
        return new ResponseEntity<>(ld, HttpStatus.OK);
    }
        @PostMapping("/docenten")
        public ResponseEntity<Object> createStudent(@Valid @RequestBody Student d, BindingResult br) {
            if (br.hasErrors()) {
                StringBuilder sb = new StringBuilder();
                for (FieldError fe : br.getFieldErrors()) {
                    sb.append(fe.getDefaultMessage());
                    sb.append("\n");
                }
                return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
            }
            else {
                repos.save(d);
                return new ResponseEntity<>("Student aangemaakt!", HttpStatus.CREATED);
            }
        }
    }


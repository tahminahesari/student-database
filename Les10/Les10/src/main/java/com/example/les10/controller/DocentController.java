package com.example.les10.controller;

import com.example.les10.model.Docent;
import com.example.les10.repository.DocentRepository;
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
public class DocentController {
    @Autowired
    DocentRepository repos;

    @GetMapping("/docenten")
    public ResponseEntity<Object> getTeachers() {
        List<Docent> ld = repos.findAll();
        return new ResponseEntity<>(ld, HttpStatus.OK);
    }

    @PostMapping("/docenten")
    public ResponseEntity<Object> createTeacher(@Valid @RequestBody Docent d, BindingResult br) {
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
            return new ResponseEntity<>("Docent aangemaakt!", HttpStatus.CREATED);
        }
    }
}

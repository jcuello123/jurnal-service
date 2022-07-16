package com.cuello.jurnal.api.logs;

import com.cuello.jurnal.repository.DiaryRepository;
import com.cuello.jurnal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LogsEndpoint {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DiaryRepository diaryRepository;

    @GetMapping
    public ResponseEntity index() {
        return new ResponseEntity(diaryRepository.getDiaries("yo"), HttpStatus.OK);
    }
}
package com.cuello.jurnal.api.logs;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LogsEndpoint {
    @GetMapping
    public ResponseEntity index() {
        return new ResponseEntity("hi", HttpStatus.OK);
    }
}
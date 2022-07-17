package com.cuello.jurnal.api.logs;

import com.cuello.jurnal.model.Log;
import com.cuello.jurnal.repository.LogRepository;
import com.cuello.jurnal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/logs")
public class LogsEndpoint {
    private UserRepository userRepository;
    private LogRepository logRepository;

    @Autowired
    public LogsEndpoint(UserRepository userRepository, LogRepository logRepository) {
        this.userRepository = userRepository;
        this.logRepository = logRepository;
    }

    @PostMapping
    public ResponseEntity getLogs(@RequestHeader String username, @RequestBody Map<String, Object> requestBody) {
        int offset = (int)requestBody.get("offset");
        List<LogResource> logResources = logRepository.getLogsByUsername(username, offset);
        return new ResponseEntity(logResources, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity saveLog(@RequestBody Log log) {
        Log logFromDB = logRepository.getLogByUsernameAndDate(log.getUsername(), log.getDate());
        if (logFromDB != null) {
            return new ResponseEntity("Log already exists for this date.", HttpStatus.CONFLICT);
        }

        Log newLog = new Log(log.getText(), log.getDate(), log.getUsername());
        Log savedLog = logRepository.saveAndFlush(newLog);

        return new ResponseEntity(savedLog, HttpStatus.CREATED);
    }
}
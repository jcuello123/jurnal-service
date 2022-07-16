package com.cuello.jurnal.api.signup;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.cuello.jurnal.model.User;
import com.cuello.jurnal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("signup")
public class SignupEndpoint {
    private UserRepository userRepository;

    @Autowired
    public SignupEndpoint(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity signup(@RequestBody User user) {
        User userInDB = userRepository.getUserByUsername(user.getUsername());
        if (userInDB != null) {
            return new ResponseEntity("User already exists", HttpStatus.CONFLICT);
        }

        String encryptedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        User newUser = new User(user.getUsername(), encryptedPassword);
        User savedUser = userRepository.saveAndFlush(newUser);

        return new ResponseEntity(savedUser, HttpStatus.CREATED);
    }
}

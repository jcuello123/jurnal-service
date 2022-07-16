package com.cuello.jurnal.api.login;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.cuello.jurnal.model.User;
import com.cuello.jurnal.repository.UserRepository;
import com.cuello.jurnal.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginEndpoint {
    private UserRepository userRepository;
    private TokenService tokenService;

    @Autowired
    public LoginEndpoint(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity login(@RequestBody User user) {
        User userInDB = userRepository.getUserByUsername(user.getUsername());
        if (userInDB == null) {
            return new ResponseEntity("User doesn't exist.", HttpStatus.NOT_FOUND);
        }

        BCrypt.Result passwordCheck = BCrypt.verifyer()
                .verify(user.getPassword().toCharArray(), userInDB.getPassword());

        if (!passwordCheck.verified) {
            return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
        }

        String token = tokenService.createNewToken(user.getUsername());
        if (token == null) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(token, HttpStatus.OK);
    }
}

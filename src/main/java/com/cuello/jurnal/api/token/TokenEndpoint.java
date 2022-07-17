package com.cuello.jurnal.api.token;

import com.cuello.jurnal.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("token")
public class TokenEndpoint {
    private TokenService tokenService;

    @Autowired
    public TokenEndpoint(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity renewExpiredToken(@RequestHeader String token, @RequestHeader String username) {
        TokenState tokenState = tokenService.getTokenState(token, username);

        if (tokenState.isValid() && tokenState.isExpired()) {
            String newToken = tokenService.createNewToken(username);
            return new ResponseEntity(newToken, HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }
}

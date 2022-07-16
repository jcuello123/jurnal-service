package com.cuello.jurnal.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

    public String createNewToken(String username) {
        String token;
        try {
            Algorithm algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));

            Date futureDate = getFutureDateUsingMinutes(10);

            token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(username)
                    .withExpiresAt(futureDate)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            return null;
        }

        return token;
    }

    private Date getFutureDateUsingMinutes(int minutes) {
        Date today = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.MINUTE, minutes);

        return c.getTime();
    }
}

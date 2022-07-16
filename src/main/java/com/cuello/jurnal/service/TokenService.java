package com.cuello.jurnal.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {
    private Algorithm algorithm;

    public TokenService() {
        this.algorithm = Algorithm.HMAC256(System.getenv("JWT_SECRET"));
    }

    public String createNewToken(String username) {
        String token;
        try {
            Date futureDate = getFutureDateUsingMinutes(10);

            token = JWT.create()
                    .withIssuer("auth0")
                    .withSubject(username)
                    .withExpiresAt(futureDate)
                    .sign(this.algorithm);
        } catch (JWTCreationException e) {
            return null;
        }

        return token;
    }

    public boolean isValid(String token, String userMakingRequest) {
        if (token == null) {
            return false;
        }

        try {
            JWTVerifier verifier = JWT.require(this.algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            String userFromToken = jwt.getSubject();
            if (!userMakingRequest.equals(userFromToken)) {
                return false;
            }

            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    private Date getFutureDateUsingMinutes(int minutes) {
        Date today = new Date();

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.MINUTE, minutes);

        return c.getTime();
    }
}

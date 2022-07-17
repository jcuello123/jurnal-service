package com.cuello.jurnal.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cuello.jurnal.api.token.TokenState;
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

    public TokenState getTokenState(String token, String userMakingRequest) {
        if (token == null || token.equals("")) {
            return new TokenState(false, "Token is null or empty.");
        }

        if (userMakingRequest == null || userMakingRequest.equals("")) {
            return new TokenState(false, "Username is null or empty.");
        }

        userMakingRequest = userMakingRequest.toLowerCase();

        try {
            JWTVerifier verifier = JWT.require(this.algorithm)
                    .withIssuer("auth0")
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            String userFromToken = jwt.getSubject().toLowerCase();
            if (!userMakingRequest.equals(userFromToken)) {
                return new TokenState(false, "Username does not match.");
            }

            return new TokenState(true,  null);
        } catch (JWTVerificationException e) {
            TokenState tokenState = new TokenState(false, e.getMessage());

            if (e.getMessage().toLowerCase().contains("expired")) {
                tokenState.setIsExpired(true);

                try {
                    JWTVerifier verifier = JWT.require(this.algorithm)
                            .withIssuer("auth0")
                            .acceptLeeway((System.currentTimeMillis() / 1000))
                            .build();

                    DecodedJWT jwt = verifier.verify(token);
                    String userFromToken = jwt.getSubject().toLowerCase();

                    if (userMakingRequest.equals(userFromToken)) {
                        tokenState.setIsValid(true);
                    }
                } catch (JWTVerificationException ex) {
                    return tokenState;
                }
            }

            return tokenState;
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

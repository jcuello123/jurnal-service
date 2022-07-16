package com.cuello.jurnal.filter;

import com.cuello.jurnal.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    private TokenService tokenService;

    @Autowired
    public AuthorizationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected  void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String token = req.getHeader("token");
        String username = req.getHeader("username");
        if (tokenService.isValid(token, username)) {
            chain.doFilter(req, res);
        }
        else {
            res.sendError(401, "Unauthorized");
        }
    }
}

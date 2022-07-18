package com.cuello.jurnal.filter;

import com.cuello.jurnal.api.token.TokenState;
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
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        if (req.getMethod().equals("OPTIONS")) {
            chain.doFilter(req, res);
            return;
        }

        String token = req.getHeader("token");
        String username = req.getHeader("username");
        TokenState tokenState = tokenService.getTokenState(token, username);

        if (tokenState.isValid() && !tokenState.isExpired()) {
            chain.doFilter(req, res);
        }
        else {
            res.sendError(401);
        }
    }
}

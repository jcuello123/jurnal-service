package com.cuello.jurnal.configuration;

import com.cuello.jurnal.filter.AuthorizationFilter;
import com.cuello.jurnal.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorizationConfig {
    private TokenService tokenService;

    @Autowired
    public AuthorizationConfig(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> filterRegistrationBean() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();

    registrationBean.setFilter(new AuthorizationFilter(tokenService));
        registrationBean.addUrlPatterns("/logs/*");
        registrationBean.setOrder(1);

        return registrationBean;
    }
}

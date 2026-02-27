package com.salonBelleza.salon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

@Configuration
public class ProjectConfig implements WebMvcConfigurer {

    @Autowired
    private SpringResourceTemplateResolver templateResolver;

    public void init() {
        templateResolver.setSuffix(".html");
    }
}

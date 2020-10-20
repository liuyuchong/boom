package com.boom.success.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> loginFilter() {
        FilterRegistrationBean<LoginFilter>
                registration = new FilterRegistrationBean<>();
        LoginFilter loginFilter = new LoginFilter();
        registration.setFilter(loginFilter);
        List<String> patterns = Arrays.asList("/api/*");
        registration.setUrlPatterns(patterns);
        registration.setOrder(1);
        return registration;
    }
}

package com.kakaopay.housing;

import com.kakaopay.housing.auth.conf.JWTFilter;
import com.kakaopay.housing.auth.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
public class HousingApplication {

    @Autowired
    UserTokenService userTokenService;

    public static void main(String[] args) {
        SpringApplication.run(HousingApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<JWTFilter> tokenFilter() {
        FilterRegistrationBean<JWTFilter> registrationBean = new FilterRegistrationBean<>();
        JWTFilter jwtFilter = new JWTFilter();
        jwtFilter.setUserTokenService(userTokenService);

        registrationBean.setFilter(jwtFilter);
        registrationBean.addUrlPatterns("/bank/*");

        return registrationBean;
    }
}

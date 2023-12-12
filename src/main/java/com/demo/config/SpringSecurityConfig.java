package com.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig {
    @Resource
    private MyAccessDeniedHandler myAccessDeniedHandler;
    @Resource
    private AuthenticationEntryPointIHandler authenticationEntryPointIHandler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.formLogin().disable()
                .csrf().and().cors().disable()
                .authorizeRequests().mvcMatchers("1", "callback").permitAll()
                .anyRequest().authenticated();

        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPointIHandler)
                .accessDeniedHandler(myAccessDeniedHandler);
        http.addFilterBefore(new MySecurityFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

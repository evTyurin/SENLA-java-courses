package com.senlainc.warsaw.tyurin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/garage-places/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/craftsmen/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/orders/**").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/orders/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/orders/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/orders/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/craftsmen/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/craftsmen/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/garage-places/**").hasAnyAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/garage-places/**").hasAnyAuthority("ADMIN");
        http
                .addFilterBefore(jwtAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }
}

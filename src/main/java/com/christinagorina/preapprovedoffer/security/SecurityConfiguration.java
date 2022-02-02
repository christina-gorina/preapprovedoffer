package com.christinagorina.preapprovedoffer.security;

import com.christinagorina.preapprovedoffer.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/offers").hasRole("ADMIN")
            .antMatchers(HttpMethod.DELETE, "/api/offers/**").hasRole("ADMIN")
            .antMatchers(HttpMethod.GET, "/api/offers/**").hasAnyRole("ADMIN", "USER")
            .antMatchers(HttpMethod.GET, "/api/offers").hasAnyRole("ADMIN", "USER")
            .antMatchers("/api/**").authenticated()
            .and()
            .formLogin()
            .successForwardUrl("/success");

    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        //упрощение для проекта
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}

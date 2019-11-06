package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration

public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("mimo")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("momo")).roles("ADMIN");

    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/getBooks").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/saveBook").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/getBookByAuthor/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/deleteBookById/**").hasRole("ADMIN")

                .and()
                .csrf().disable().formLogin().disable();

    }
@Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
}

}

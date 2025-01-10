package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //users with role USER can access all /user pages and users with role ADMIN can access /admin pages
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll() // Public pages
                .antMatchers("/admin/**").hasRole("ADMIN") // Admin pages
                .antMatchers("/user/**").hasRole("USER") // User pages
                .anyRequest().authenticated() // Other requests need authentication
            .and()
            .formLogin()
                .loginPage("/login") // Custom login page (optional)
                .permitAll()
            .and()
            .logout()
                .permitAll();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        // In-memory user details (for demo purposes)
    
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build());
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build());
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

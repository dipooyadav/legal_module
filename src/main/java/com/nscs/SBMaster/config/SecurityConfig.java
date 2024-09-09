package com.nscs.SBMaster.config;

import jakarta.servlet.ServletContext;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                try {
                    MessageDigest md = MessageDigest.getInstance("MD5");
                    byte[] digest = md.digest(rawPassword.toString().getBytes());
                    StringBuilder sb = new StringBuilder();
                    for (byte b : digest) {
                        sb.append(String.format("%02x", b));
                    }
                    return sb.toString();
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encode(rawPassword).equals(encodedPassword);
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // Bean to handle session expiration events (for concurrent session management)
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    // Set session timeout programmatically
    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return new ServletContextInitializer() {
            @Override
            public void onStartup(ServletContext servletContext) {
                servletContext.getSessionCookieConfig().setMaxAge(1800); // Set session timeout to 30 minutes
                servletContext.setSessionTimeout(30); // Set session timeout to 30 minutes
            }
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (ensure this is appropriate for your context)
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/register", "/menu/userLoginMenus/{ustid}").permitAll() // Allow unauthenticated access to /register
                        .requestMatchers("/signin", "/saveUser").permitAll() // Other public endpoints
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/signin")
                        .loginProcessingUrl("/userlogin")
                        .defaultSuccessUrl("/AllIndex", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/custom-logout")
                        .logoutSuccessUrl("/signin?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .invalidSessionUrl("/signin?session=invalid") // Redirect if session is invalid
                        .maximumSessions(1) // Allow only one concurrent session per user
                        .expiredUrl("/signin?session=expired") // Redirect if session expires
                        .maxSessionsPreventsLogin(true) // Prevent new login if max sessions are reached
                        .and()
                        .sessionFixation().migrateSession() // Prevent session fixation attacks
                );;
        return http.build();
    }
}

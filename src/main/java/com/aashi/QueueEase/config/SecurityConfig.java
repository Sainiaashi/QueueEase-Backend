package com.aashi.QueueEase.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Autowired
private OAuth2SuccessHandler oAuth2SuccessHandler;
   @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .oauth2Login(oauth2 -> oauth2
    .successHandler(oAuth2SuccessHandler)
)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/auth/**").permitAll()
            .requestMatchers("/oauth2/**", "/login/oauth2/**").permitAll()
            .anyRequest().authenticated()
        );
    return http.build();
}

   @Bean
   public PasswordEncoder passwordEncoder()
   {
    return new BCryptPasswordEncoder();
   } 
}

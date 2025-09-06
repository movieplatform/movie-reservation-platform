package com.example.movieplatform.common.config;

import com.example.movieplatform.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
     }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http
                 .authorizeHttpRequests((auth) -> auth
                         .requestMatchers( "/","/login", "/register").permitAll()
                         .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                         .requestMatchers("/admin/**").hasRole("ADMIN")
                         .requestMatchers("/my-page", "/my-page/**").hasAnyRole("ADMIN","USER")
                         .anyRequest().authenticated()
                 )

                 .formLogin((auth) -> auth
                         .loginPage("/login")
                         .defaultSuccessUrl("/", true)
                         .permitAll()
                 )

                 .oauth2Login((oauth2)-> oauth2
                         .loginPage(("/login"))
                         .userInfoEndpoint((userInfoEndpointConfig ->
                                 userInfoEndpointConfig.userService(customOAuth2UserService)))
                                 .defaultSuccessUrl("/", true)
                 );


         http
                 .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}

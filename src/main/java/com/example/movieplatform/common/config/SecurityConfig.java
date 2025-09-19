package com.example.movieplatform.common.config;

import com.example.movieplatform.auth.service.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
     }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         http
                 .csrf(AbstractHttpConfigurer::disable)
                 .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                 .authorizeHttpRequests((auth) -> auth
                         // 공개 API
                         .requestMatchers("/api/register", "/api/login", "/api/session", "/api/logout", "/api/movies/**" ).permitAll()
                         .requestMatchers(HttpMethod.GET, "/api/reviews/**").permitAll()
                         // 관리자 API
                         .requestMatchers("/api/admin/**").hasRole("ADMIN")
                         // 기타 API는 인증 필요
                         .anyRequest().authenticated()

                 )


                 .formLogin(AbstractHttpConfigurer::disable)
                 .httpBasic(AbstractHttpConfigurer::disable)

                 .oauth2Login(oauth2 -> oauth2
                         .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                         .successHandler((request, response, authentication) -> {
                             // 로그인 성공 후 프론트로 이동
                             response.sendRedirect("http://localhost:3000/");
                         })
                 )
                 // 여기 추가 👇
                 .exceptionHandling(ex -> ex
                         .authenticationEntryPoint((request, response, authException) -> {
                             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 로그인 안 된 경우 401 반환
                         })
                 );


         http
                 .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);  // 쿠키 허용
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}

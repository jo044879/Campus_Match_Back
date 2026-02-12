package com.pigs.holiday.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pigs.holiday.repository.ClubRepository;
import com.pigs.holiday.security.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    private final ClubRepository clubRepository;
    private final ObjectMapper objectMapper;
    private final AuthService authService;
    private final ExternalProperties externalProperties;

    // Jackson ObjectMapper bean
    @Bean
    static ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    /**
     * 비밀번호 암호화
     */
    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Spring Security 권한 및 필터 설정
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // iframe 허용

        http
                // 1. CORS 설정 적용 (하단에 만든 corsConfigurationSource 메서드를 사용)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. 기본 보안 설정 비활성화 (REST API이므로)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)

                // 3. 세션 사용 안 함 (Stateless)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 4. 요청 권한 설정 (현재는 모두 허용)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())

                // 5. 커스텀 필터 등록 (JWT 등)
                .apply(new CustomDsl());

        return http.build();
    }

    /**
     * CORS 설정
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        // 1. 허용할 출처 (프론트엔드 배포 주소 + 로컬 테스트용)
        config.setAllowedOrigins(Arrays.asList(
                "https://campus-match-front.vercel.app", // 배포된 프론트 주소
                "http://localhost:3000"                    // (선택) 로컬 개발용
        ));

        // 2. 허용할 HTTP 메서드
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));

        // 3. 허용할 헤더
        config.setAllowedHeaders(Arrays.asList("*"));

        // 4. 자격 증명 허용 (쿠키, Authorization 헤더 등)
        config.setAllowCredentials(true);

        // 5. 브라우저가 읽을 수 있도록 허용할 헤더 (JWT 토큰 반환 시 필요)
        config.setExposedHeaders(Arrays.asList("Authorization", "RefreshToken"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    public class CustomDsl extends AbstractHttpConfigurer<CustomDsl, HttpSecurity> {

        /**
         * 커스텀 필터 설정 (로그인, 인증 필터 등)
         */
        @Override
        public void configure(HttpSecurity http) {
            AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);

            JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, objectMapper, authService, externalProperties);
            jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");

            http
                    // 로그인 필터
                    .addFilter(jwtAuthenticationFilter)
                    // JWT 검증 필터
                    .addFilter(new JwtAuthorizationFilter(authenticationManager, clubRepository, authService, externalProperties))
                    // 예외 처리 필터
                    .addFilterBefore(new FilterExceptionHandlerFilter(), BasicAuthenticationFilter.class);
        }

    }

}
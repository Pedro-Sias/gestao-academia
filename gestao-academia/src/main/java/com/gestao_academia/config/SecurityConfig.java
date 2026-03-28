package com.gestao_academia.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // 1. Acesso Público
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()

                        // 2. Acesso exclusivo do MESTRE (ADMIN) - O Financeiro e Gestão de Staff
                        .requestMatchers("/api/relatorios/**").hasRole("ADMIN")
                        .requestMatchers("/api/pagamentos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/mensalidade/*/pagar").hasRole("ADMIN")
                        .requestMatchers("/api/professores/**").hasRole("ADMIN")

                        // 3. Acesso PROFESSOR e ADMIN - Parte Técnica e Alunos
                        // Professor pode criar exercícios e montar treinos
                        .requestMatchers(HttpMethod.POST, "/api/exercicios/**").hasAnyRole("ADMIN", "PROFESSOR")
                        .requestMatchers("/api/treinos/**").hasAnyRole("ADMIN", "PROFESSOR")
                        .requestMatchers("/api/alunos/**").hasAnyRole("ADMIN", "PROFESSOR")
                        .requestMatchers("/api/aulas/**").hasAnyRole("ADMIN", "PROFESSOR")

                        // 4. Acesso ALUNO - Ver o próprio progresso
                        // Aqui o aluno só pode dar GET nas coisas dele
                        .requestMatchers("/api/reservas/**").authenticated()
                        .requestMatchers("/api/historico-cargas/**").authenticated()
                        .requestMatchers(HttpMethod.GET, "/api/treinos/meu-treino").hasRole("ALUNO")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
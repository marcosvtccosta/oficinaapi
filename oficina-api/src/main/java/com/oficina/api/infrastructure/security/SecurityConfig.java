package com.oficina.api.infrastructure.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${USER_ROLE:USER}")
    private String userRole;
    @Value("${ADMIN_ROLE:ADMIN}")
    private String adminRole;
    @Value("${USER_USERNAME:user}")
    private String userUsername;
    @Value("${ADMIN_USERNAME:admin}")
    private String adminUsername;
    @Value("${USER_PASSWORD:password}")
    private String userPassword;
    @Value("${ADMIN_PASSWORD:admin}")
    private String adminPassword;

    // Filtro de segurança padrão (HTTP Basic) - ativo quando o perfil oauth2 NÃO estiver habilitado
    @Bean
    @Profile("!oauth2")
    public SecurityFilterChain basicSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    // Usuários in-memory para HTTP Basic (somente no perfil padrão)
    @Bean
    @Profile("!oauth2")
    public UserDetailsService users() {
        UserDetails user = User.withUsername(userUsername).password("{noop}" + userPassword).roles(userRole).build();
        UserDetails admin = User.withUsername(adminUsername).password("{noop}" + adminPassword).roles(adminRole).build();
        return new InMemoryUserDetailsManager(user, admin);
    }

}

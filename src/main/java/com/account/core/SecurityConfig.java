package com.account.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));
        http.csrf(csrf -> csrf.disable());
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/oauth/**", "/login/**", "/logout/**", "/actuator/**", "/plugins/**", "/encrypt").permitAll()
                .anyRequest().authenticated()
        );
        http.formLogin(form -> form
                .loginPage("/login.html")
                .loginProcessingUrl("/authentication/form")
                .failureUrl("/login-error.html")
                .defaultSuccessUrl("/index.html", true)
                .permitAll()
        );
        return http.build();
    }

    @Bean
    public UserDetailsService users(PasswordEncoder encoder) {
        UserDetails admin = User.withUsername("admin").password(encoder.encode("admin")).roles("VIP1", "VIP2").build();
        UserDetails xiaxinyu = User.withUsername("xiaxinyu").password(encoder.encode("xiaxinyu")).roles("VIP2", "VIP3").build();
        UserDetails pengshenglan = User.withUsername("pengshenglan").password(encoder.encode("pengshenglan")).roles("VIP1", "VIP3").build();
        return new InMemoryUserDetailsManager(admin, xiaxinyu, pengshenglan);
    }
}

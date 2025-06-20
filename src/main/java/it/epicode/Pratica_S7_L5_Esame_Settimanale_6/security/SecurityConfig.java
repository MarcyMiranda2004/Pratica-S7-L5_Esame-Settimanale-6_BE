package it.epicode.Pratica_S7_L5_Esame_Settimanale_6.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            JwtFilter jwtFilter
    ) throws Exception {

        httpSecurity.formLogin(http -> http.disable());
        httpSecurity.csrf(http -> http.disable());
        httpSecurity.sessionManagement(http -> http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        httpSecurity.cors(Customizer.withDefaults());

        httpSecurity.authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**").permitAll()

                // Eventi
                .requestMatchers(HttpMethod.GET, "/eventi/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/eventi/**").hasAuthority("EVENT_CREATOR")
                .requestMatchers(HttpMethod.PUT, "/eventi/**").hasAuthority("EVENT_CREATOR")
                .requestMatchers(HttpMethod.DELETE, "/eventi/**").hasAuthority("EVENT_CREATOR")

                // Prenotazioni
                .requestMatchers(HttpMethod.POST, "/prenotazioni").hasAuthority("USER")
                .requestMatchers(HttpMethod.DELETE, "/prenotazioni/**").hasAuthority("USER")
                .requestMatchers(HttpMethod.GET, "/prenotazioni").hasAuthority("USER")
                .requestMatchers(HttpMethod.GET, "/prenotazioni/**").hasAuthority("USER")

                .anyRequest().denyAll()
        );

        httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(List.of("*"));
        corsConfiguration.setAllowedMethods(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);

        return source;
    }
}

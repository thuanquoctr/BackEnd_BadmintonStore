package store.badminton.BadmintonStore.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import store.badminton.BadmintonStore.security.CustomUserDetailService;
import store.badminton.BadmintonStore.security.Endpoints;
import store.badminton.BadmintonStore.security.JwtAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
    private final String[] PRIVATE_URLS = {
            "/api/products/*/add", "/api/products/image/upload/*"
    };
    private CustomUserDetailService customUserDetailService;
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    public SecurityConfiguration(CustomUserDetailService customUserDetailService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.customUserDetailService = customUserDetailService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(this.customUserDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(
                configure -> configure
                        .requestMatchers(HttpMethod.GET, Endpoints.PUBLIC_GET_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.POST, Endpoints.PUBLIC_POST_ENDPOINTS).permitAll()
                        .requestMatchers(HttpMethod.GET, Endpoints.GET_USER_ENDPOINTS).hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.POST, Endpoints.POST_USER_ENDPOINTS).hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.PUT, Endpoints.PUT_USER_ENDPOINTS).hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.DELETE, Endpoints.DELETE_USER_ENDPOINTS).hasAuthority("ROLE_USER")
                        .requestMatchers(HttpMethod.GET, Endpoints.PRIVATE_GET_ENDPOINTS).hasAuthority("ROLE_ADMIN")
                        .requestMatchers(HttpMethod.POST, Endpoints.PRIVATE_POST_ENDPOINTS).hasAuthority("ROLE_ADMIN")
                        .anyRequest().authenticated());
        http.cors(cors -> {
            cors.configurationSource(request -> {
                CorsConfiguration corsConfiguration = new CorsConfiguration();
                corsConfiguration.addAllowedOrigin(Endpoints.front_end_host);
                corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                corsConfiguration.addAllowedHeader("*");
                corsConfiguration.setAllowCredentials(true);
                return corsConfiguration;
            });
        });
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}

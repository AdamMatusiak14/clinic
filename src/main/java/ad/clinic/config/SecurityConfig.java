package ad.clinic.config;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration

public class SecurityConfig {

        @Autowired
        DoctorAuthenticationProvider doctorAuthenticationProvider;

        @Autowired
        PatientAuthenticationProvider patientAuthenticationProvider;

        @Bean
        public AuthenticationManager authManager(HttpSecurity http) throws Exception {
                AuthenticationManagerBuilder authenticationManagerBuilder = http
                                .getSharedObject(AuthenticationManagerBuilder.class);
                authenticationManagerBuilder.authenticationProvider(doctorAuthenticationProvider);
                authenticationManagerBuilder.authenticationProvider(patientAuthenticationProvider);

                return authenticationManagerBuilder.build();
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http,
                        AuthenticationManager authManager) throws Exception {
                PathRequest.H2ConsoleRequestMatcher h2ConsoleRequestMatcher = PathRequest.toH2Console();
                http.authorizeHttpRequests(requests -> requests
                                .mvcMatchers("/").permitAll()
                                .requestMatchers(h2ConsoleRequestMatcher).permitAll()
                                .mvcMatchers("/registration", "/confirmation", "/savevisit").permitAll()
                                .mvcMatchers("/listPatient", "visit").hasRole("PATIENT")
                                .mvcMatchers("/doctor", "/grafik", "/patientCardDoc").hasRole("DOCTOR")
                                .anyRequest().authenticated());
                http.authorizeHttpRequests(requests -> requests
                                .and()
                                // .mvcMatcher("/")
                                .authenticationManager(authManager));
                http.csrf(csrf -> csrf.ignoringRequestMatchers(h2ConsoleRequestMatcher));
                http.headers().frameOptions().sameOrigin();

                http.formLogin(login -> login.loginPage("/login").permitAll());
                http.csrf().disable();
                return http.build();
        }

        @Bean
        PasswordEncoder passwordEncoder() {
                return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        }

}

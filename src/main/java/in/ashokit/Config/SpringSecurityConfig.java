package in.ashokit.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                // Public URLs
                .requestMatchers(
                        "/",
                        "/login",
                        "/signup",
                        "/register",
                        "/css/**",
                        "/js/**",
                        "/favicon.ico"
                ).permitAll()

                // USER + ADMIN can view books
                .requestMatchers(
                        "/viewBooks"
                ).hasAnyRole("USER", "ADMIN")

                // ADMIN only
                .requestMatchers(
                        "/addBook",
                        "/saveBook",
                        "/viewBooks",
                        "/edit",
                        "/delete"
                ).hasRole("ADMIN")

                // Any other URL requires login
                .anyRequest()
                .authenticated()
            )

            .formLogin(form -> form
                    .loginPage("/login")
                    .usernameParameter("email")
                    .defaultSuccessUrl("/viewBooks", true)
                    .permitAll()
            )

            .logout(logout -> logout
                    .logoutSuccessUrl("/login")
                    .permitAll()
            );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
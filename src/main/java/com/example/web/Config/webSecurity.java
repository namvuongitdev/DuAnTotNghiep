package com.example.web.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class webSecurity {

    @Autowired
    private AuthenticationSuccessHandlers successHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().requestMatchers("/WEB-INF/view/**", "/css/**", "/anh/**", "/fonts/**"
                , "/img/**", "/js/**", "/index/**" , "/image/**" ).permitAll()
                .requestMatchers("/admin/**").hasAnyAuthority("ADMIN" , "STAFF")
                .requestMatchers("/gio-hang-onl/**" , "/cuaToi/**", "/checkouts/**").hasAnyAuthority("ADMIN" , "STAFF", "CLIENT")
                .and().formLogin().loginPage("/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .successHandler(successHandler)
                .loginProcessingUrl("/dang-nhap")
                .and()
                .logout().logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login").and()
                .csrf().disable();
        return http.build();
    }
}
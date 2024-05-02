package com.example.yara.configurations;

import com.example.yara.Handlers.CustomAuthenticationSuccessHandler;
import com.example.yara.Service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.awt.image.ImageFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration{
    private final UserDetailService userDetailService;
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
        return userDetailService;
    }

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailService);
        return provider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/adminPanel/**").hasRole("ADMIN")
                        .requestMatchers("/teacherPanel/**").hasRole("TEACHER")
                        .requestMatchers("/homePage","/study/**").authenticated()
                        .requestMatchers("/","/RegisterPage","/registration").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                        .requestMatchers("/css/").permitAll()
                        .requestMatchers("/img/**").permitAll())
                .formLogin(form -> form.loginPage("/LoginPage").permitAll().loginProcessingUrl("/login")
                        .successHandler(authenticationSuccessHandler))
                .build();
    }




}

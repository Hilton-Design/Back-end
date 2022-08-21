package com.hilton.hibye.global.security;

import com.hilton.hibye.global.security.jwt.JwtTokenProvider;
import com.hilton.hibye.global.security.jwt.JwtValidateService;
import com.hilton.hibye.global.security.jwt.filter.JwtAuthenticationFilter;
import com.hilton.hibye.global.security.jwt.filter.JwtExceptionFilter;
import com.hilton.hibye.global.security.oauth.AuthDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthDetailsService authDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtValidateService jwtValidateService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .formLogin().disable()
                .cors().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                .antMatchers("*").permitAll()
        ;

        http
                .addFilterBefore(new JwtAuthenticationFilter(authDetailsService, jwtTokenProvider, jwtValidateService),
                        UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JwtExceptionFilter(), JwtAuthenticationFilter.class);

    }
}

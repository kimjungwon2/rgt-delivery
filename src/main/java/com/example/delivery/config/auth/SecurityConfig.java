package com.example.delivery.config.auth;

import com.example.delivery.domain.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    private static final String[] PUBLIC_URI = {
            "/api/order",
            "/api/modify"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                //리소스 별 허용 범위
                .authorizeRequests()
                .antMatchers(PUBLIC_URI).permitAll()
                .antMatchers("/api/login").hasRole(Role.USER.name())
                .anyRequest().authenticated()

                .and()
                .logout()
                .logoutSuccessUrl("/")

                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);

    }
}

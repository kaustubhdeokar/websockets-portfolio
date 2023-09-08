package com.deokarkaustubh.websocketmessaging.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
public class SecurityConfiguration {

    private static final String securePassword = "";

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //noinspection removal
        return http
                .csrf().disable()
                .formLogin()
                .loginPage("/index.html")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/chat.html")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index.html")
                .permitAll()
                .and()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/js/**", "/lib/**", "/images/**", "/css/**", "/index.html", "/").permitAll()
                            .requestMatchers("/websocket").permitAll()
                            .anyRequest().permitAll();
                }).build();

    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(new AuthenticationProvider() {

            @Override
            public boolean supports(Class<?> authentication) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
            }

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

                List<GrantedAuthority> authorities = "".equals(token.getCredentials()) ?
                        AuthorityUtils.createAuthorityList("ROLE_ADMIN") : null;

                return new UsernamePasswordAuthenticationToken(token.getName(), token.getCredentials(), authorities);
            }
        });
    }


}

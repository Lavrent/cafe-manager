package com.cafe.manager.web.security;

import com.cafe.manager.web.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CafeManagementAuthenticationProvider authProvider;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String manager = UserType.MANAGER.getType();

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users/first-manager").permitAll()
                .antMatchers(HttpMethod.POST, "/users", "/products", "/tables").hasRole(manager)
                .antMatchers(HttpMethod.PUT, "/users/**").hasRole(manager)
                .antMatchers("/**").hasAnyRole(manager, UserType.WAITER.getType())
                .and()
                .httpBasic();
    }
}
package com.gmail.merikbest2015.ecommerce.configuration;

import com.gmail.merikbest2015.ecommerce.security.JwtConfigurer;
import com.gmail.merikbest2015.ecommerce.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    @Autowired
    public WebSecurityConfiguration(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/rest",
                        "/api/v1/rest/product/*",
                        "/api/v1/rest/admin/*",
                        "/api/v1/rest/admin/user/*",
                        "/api/v1/rest/registration",
                        "/api/v1/rest/forgot",
                        "/api/v1/rest/reset/**",
                        "/api/v1/rest/activate/*",
                        "/api/v1/rest/menu/**",
                        "/api/v1/rest/cart",
                        "/api/v1/rest/cart/*",
                        "/api/v1/rest/order",
                        "/api/v1/rest/order/*",
                        "/api/v1/rest/user/*",
                        "/api/v1/rest/brands",
                        "/img/**",
                        "/static/**",
                        "/activate/*",
                        "/menu/**").permitAll()
                .antMatchers("/api/v1/rest/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .apply(jwtConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

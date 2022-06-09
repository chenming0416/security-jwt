package com.example.securitydemo.config;

import com.example.securitydemo.hander.LoginFailureHander;
import com.example.securitydemo.hander.LoginSuccessHander;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHander loginSuccessHander;
    @Autowired
    private LoginFailureHander loginFailureHander;
    @Bean
    public PasswordEncoder passwordEncoder(){
        // 定义一个密码的hash方式，这个是security推荐的，随机盐10位自动加在hash里了
        return new BCryptPasswordEncoder();
    }

    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable()
                .formLogin()
                .successHandler(loginSuccessHander)
                .failureHandler(loginFailureHander);
    }


}

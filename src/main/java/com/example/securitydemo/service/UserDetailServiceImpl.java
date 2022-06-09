package com.example.securitydemo.service;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //使用此用户名从数据库里查出用户数据，返回一个user对象。下面是模拟数据
        // pwd就是从数据库里查出来的数据。保存的是hash后的密码
        String pwd = passwordEncoder.encode("123");
        System.out.println(username);
        // 返回数据库对象，框架方法会自动和前台输入比较，权限值可以传多个
        return new User(username,pwd, AuthorityUtils.commaSeparatedStringToAuthorityList("admin,guest"));
    }
}

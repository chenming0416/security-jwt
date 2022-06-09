package com.example.securitydemo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class SecurityDemoApplicationTests {

    @Test
    void contextLoads() {
        long currenttime = System.currentTimeMillis();
        long expiretime = currenttime+1000*3;
        JwtBuilder jwtBuilder = Jwts.builder()
                // 唯一的id
                .setId("999")
                // 创建的时间
                .setIssuedAt(new Date())
                // 接受的用户
                .setSubject("abc")
                // 签名和秘钥
                .signWith(SignatureAlgorithm.HS256,"123456")
                // 设置过期时间
                .setExpiration(new Date(expiretime))
                // 自定义内容
                .claim("name","jsonwebtoken");

        String token = jwtBuilder.compact();
        System.out.println(token);
        System.out.println("====================");
        Claims claims = (Claims)Jwts.parser()
                .setSigningKey("123456")
                .parse(token)
                .getBody();
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getId());
        System.out.println(claims.getExpiration());
        System.out.println(claims.get("name"));

    }

    @Test
    public void t1(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI5OTkiLCJpYXQiOjE2NTQyNTc3NjAsInN1YiI6ImFiYyIsImV4cCI6MTY1NDI1Nzc2MywibmFtZSI6Impzb253ZWJ0b2tlbiJ9.IUJXk8AHdhtOXNN1mq-crrf8P7irDNm4Bhr7KK-4px0";
        Claims claims = (Claims)Jwts.parser()
                .setSigningKey("123456")
                .parse(token)
                .getBody();
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getId());
        System.out.println(claims.getExpiration());
        System.out.println(claims.get("name"));
    }

}

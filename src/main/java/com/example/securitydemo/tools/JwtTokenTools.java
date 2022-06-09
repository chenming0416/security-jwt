package com.example.securitydemo.tools;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenTools {
    public String generateJwtToken(String username){
        long currenttime = System.currentTimeMillis();
        long expiretime = currenttime+1000*3;
        JwtBuilder jwtBuilder = Jwts.builder()
                // 唯一的id,{"jti":"888"}
                .setId("999")
                // 创建的时间,{"ita":"XXXXXXXXXXXX"}
                .setIssuedAt(new Date())
                // 接受的用户,{"sub":"rose"}
                .setSubject(username)
                // 签名和秘钥,参数1，算法，参数2，盐，,{"alg":"HS256"}
                .signWith(SignatureAlgorithm.HS256,"123456")
                // 设置过期时间
                .setExpiration(new Date(expiretime))
                // 自定义内容
                .claim("name","jsonwebtoken");

        String token = jwtBuilder.compact();
        return token;
    }
    public String getUsernameFromToken(String token){
        Claims claims = (Claims)Jwts.parser()
                .setSigningKey("123456")
                .parse(token)
                .getBody();
        String username = claims.getSubject();
        System.out.println(claims.getSubject());
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getId());
        System.out.println(claims.getExpiration());
        System.out.println(claims.get("name"));
        return username;
    }
}

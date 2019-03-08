package com.test.common;

import java.util.Date;

import javax.servlet.ServletException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtils {
    final static String base64EncodedSecretKey = "base64EncodedSecretKey";// 私钥
    final static long TOKEN_EXP = 1000 * 60 * 60 * 24;// 过期时间, 1000 * 60测试使用60秒

    public static String getToken(String userName) {
        return Jwts.builder().setSubject(userName).claim("username", userName).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /* 过期时间 */
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey).compact();
    }

    // 解析token
    public static String checkToken(String token) throws ServletException {
        String username = null;
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
            System.out.println("从token中解析到的username==" + claims);
            username = (String) claims.get("username");
            System.out.println("username==" + username);            
        } catch (ExpiredJwtException e1) {
            throw new ServletException("token expired");            
        } catch (Exception e) {
            throw new ServletException("other token exception");            
        }
        return username;
    }

}
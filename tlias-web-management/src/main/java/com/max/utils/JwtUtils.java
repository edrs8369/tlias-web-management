package com.max.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // Base64 編碼的密鑰（和你單元測試一致）
    private static final String SECRET_KEY_BASE64 = "aXRoZWltYVNjaG9vbE1heHRvQmVSaWNoTWFuWWVzSGVDYW4=";

    // 過期時間：12 小時（毫秒）
    private static final long EXPIRATION_MILLIS = 12 * 60 * 60 * 1000;

    // 將 Base64 密鑰轉成 SecretKeyx`
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY_BASE64));

    /**
     * 生成 JWT 令牌
     * @param claims 自定義的 Claim 信息（如 id、username 等）
     * @return JWT 字串
     */
    public static String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MILLIS))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 解析 JWT 令牌
     * @param token JWT 字串
     * @return Claims，自定義信息
     * @throws JwtException 解析失敗時拋出
     */
    public static Claims parseToken(String token) throws JwtException {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}


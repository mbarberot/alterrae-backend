package com.sistearth.spark.token;

import com.sistearth.api.beans.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;

public class TokenManager {

    public static final Key KEY = MacProvider.generateKey(HS512);

    public String createToken(User user) {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(HS512, KEY)
                .compact();
    }

    public String decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}

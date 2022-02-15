package com.coderhouse.ecommerce.security;

import com.coderhouse.ecommerce.config.AppProperties;
import com.coderhouse.ecommerce.util.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtProvider implements Serializable {

    @Autowired
    private AppProperties properties;

    public String getJWTToken(String username) {
        var grantedAuthorities =
                AuthorityUtils.commaSeparatedStringToAuthorityList(Constants.ROLE);

        return Jwts.builder()
                .setSubject(username)
                .claim(Constants.AUTHORITIES,
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + properties.getExpiration()))
                .signWith(SignatureAlgorithm.HS512, properties.getJwtSecret().getBytes())
                .compact();
    }

    public boolean isExpired(String token) {
        try {
            var tokenOnly = token.substring(0, token.lastIndexOf('.') + 1);
            var expiration = ((Claims)Jwts.parser().parse(tokenOnly).getBody()).getExpiration();
        }
        catch(ExpiredJwtException e) { return true; }
        return false;
    }
}

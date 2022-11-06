package com.univates.vitaldonationapi.domain.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.univates.vitaldonationapi.domain.exception.security.InvalidTokenException;
import com.univates.vitaldonationapi.domain.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public abstract class TokenManager {

    private static final String SECRET = "secret";
    private static final String CLAIM_ROLES = "roles";
    private static final String JWT_TYPE = "Bearer";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET.getBytes());
    private static final JWTVerifier VERIFIER = JWT.require(ALGORITHM).build();
    private static final Duration TOKEN_ACCESS_EXPIRES = Duration.ofMinutes(30);
    private static final Duration TOKEN_REFRESH_EXPIRES = Duration.ofHours(1);

    private TokenManager() {
        throw new IllegalStateException("Utility class");
    }

    public static Map<String, String> createTokens(HttpServletRequest request, User user) {
        return Map.of(
            "access_token", createAccessToken(request, user),
            "refresh_token", createRefreshToken(request, user)
        );
    }

    public static Map<String, String> refreshToken(HttpServletRequest request, UserService userService) {
        String authenticationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
            String refreshToken = authenticationHeader.substring("Bearer ".length());
            DecodedJWT decodedJWT = VERIFIER.verify(refreshToken);
            String cpf = decodedJWT.getSubject();
            User user = userService.findByCpf(cpf).toUserAuth();
            return createTokens(request, user);
        }

        return null;
    }

    public static UsernamePasswordAuthenticationToken authenticateToken(HttpServletRequest request) {
        String authenticationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (isNull(authenticationHeader) || !authenticationHeader.startsWith(JWT_TYPE)) {
            throw new InvalidTokenException();
        }

        DecodedJWT decodeJWT = VERIFIER.verify(authenticationHeader.substring(JWT_TYPE.length() + 1));

        String login = decodeJWT.getSubject();
        var roles = decodeJWT.getClaim(CLAIM_ROLES).asList(String.class).stream()
                .map(SimpleGrantedAuthority::new)
                .toList();

        return new UsernamePasswordAuthenticationToken(login, null, roles);
    }

    private static String createAccessToken(HttpServletRequest request, User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.from(ZonedDateTime.now().plus(TOKEN_ACCESS_EXPIRES)))
                .withIssuer(request.getRequestURL().toString())
                .withClaim(CLAIM_ROLES, user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .sign(ALGORITHM);
    }

    private static String createRefreshToken(HttpServletRequest request, User user) {
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.from(ZonedDateTime.now().plus(TOKEN_REFRESH_EXPIRES)))
                .withIssuer(request.getRequestURL().toString())
                .sign(ALGORITHM);
    }

}


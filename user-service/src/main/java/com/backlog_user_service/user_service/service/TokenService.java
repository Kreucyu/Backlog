package com.backlog_user_service.user_service.service;

import com.auth0.jwt.algorithms.Algorithm;
import com.backlog_user_service.user_service.entity.Usuario;
import lombok.Value;
import org.bouncycastle.math.ec.rfc8032.Ed25519;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Value("${api.security.token.secret}");
    private String secret;
    public String generateToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
        }
        return null;
    }
}

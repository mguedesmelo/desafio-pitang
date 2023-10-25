package br.com.car.rental.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.car.rental.model.User;
import br.com.car.rental.shared.Constants;

@Service
public class TokenService {
	public String generateToken(User user) {
		return JWT.create()
                .withIssuer(Constants.JWT_ISSUER)
                .withSubject(user.getUsername())
                .withClaim("id", user.getId())
                .withExpiresAt(LocalDateTime.now()
                        .plusMinutes(10)
                        .toInstant(ZoneOffset.of("-03:00"))
                ).sign(Algorithm.HMAC256(Constants.JWT_SECRET_KEY));
	}
	
    public String getSubject(String token) {
        return JWT.require(Algorithm.HMAC256(Constants.JWT_SECRET_KEY))
                .withIssuer(Constants.JWT_ISSUER)
                .build().verify(token).getSubject();

    }
}

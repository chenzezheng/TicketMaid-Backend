package com.whu.ticket.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class JwtUtil {
    private static final String SECRET = "ticket_maid_secret_key.";
    private static Algorithm algorithm = Algorithm.HMAC256(SECRET);
    private static JWTVerifier verifier = JWT.require(algorithm).build();

    public static String createToken(String userId, long expire, String type) {
        try {
            Date date = new Date(System.currentTimeMillis() + expire);
            String token = JWT.create()
                    .withAudience(userId)
                    .withExpiresAt(date)
                    .withClaim("token_type", type)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            System.out.print(exception.getMessage());
            return null;
        }
    }

    public static int getUserID(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            final Integer integer = Integer.valueOf(userId);
            return integer;
        } catch (JWTDecodeException exception) {
            System.out.print(exception.getMessage());
            return 0;
        }
    }

    public static String getTokenType(String token) {
        try {
            String token_type = JWT.decode(token).getClaim("token_type").asString();
            return token_type;
        } catch (JWTDecodeException exception) {
            System.out.print(exception.getMessage());
            return null;
        }
    }

    public static boolean verifyToken(String token) {
        if (StringUtils.isBlank(token)) {
            throw new RuntimeException("无token，请重新登录");
        }
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("token无效，请重新获取");
        }
    }
}

package epam.training.finalproject.web.jwt;

import com.fasterxml.jackson.databind.json.JsonMapper;
import epam.training.finalproject.web.security.UserPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.io.JacksonSerializer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
//@PropertySource(value = {"classpath:jwt.properties"})
public class JwtTokenProvider {

    private static final Logger LOGGER = Logger.getLogger(JwtTokenProvider.class);

    @Autowired
    private Environment env;

    private static final SecretKey jwtSecret = MacProvider.generateKey(SignatureAlgorithm.HS512);//env.getProperty("app.jwtSecret");
    private final int jwtExpirationInMs = 604800000;//Integer.parseInt(env.getRequiredProperty("app.jwtExpirationInMs"));

    public String generateToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .serializeToJsonWith(new JacksonSerializer(new JsonMapper()))
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(jwtSecret)
                .compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            LOGGER.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            LOGGER.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            LOGGER.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            LOGGER.error("Unsupported JWT token");
        } catch (Exception ex) {
            LOGGER.debug(ex.getMessage());
            LOGGER.error("JWT claims string is empty.");
        }
        return false;
    }
}

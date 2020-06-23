package br.com.jobtechIO.configuration.security;

import br.com.jobtechIO.domain.entities.Candidate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class TokenService {

    @Value("$(jwt.secret)")
    private String secret;

    Date now = new Date();
    Date dateExpiration = new Date(now.getTime() + 10800000); //3 horas para expirar

    public String generateToken(Authentication authenticate) {
        Candidate userlogged = (Candidate) authenticate.getPrincipal();
        return Jwts.builder()
                .setIssuer("API Jobtech.IO")
                .setSubject(userlogged.getId().toString())
                .setIssuedAt(now)
                .setExpiration(dateExpiration)
                .signWith(SignatureAlgorithm.HS256,secret).compact();
    }

    public boolean tokenIsValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Integer getIdClient(String token) {

        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Integer.parseInt(claims.getSubject());
    }
}

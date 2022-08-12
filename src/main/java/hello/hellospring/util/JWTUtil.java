package hello.hellospring.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultJws;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;
import java.util.Date;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class JWTUtil {

    private String secretKey = "charlie12345678";

    private long expire = 60 * 24 * 30;

    public String generateToken(String content) throws Exception {

        return Jwts.builder()
            .setIssuedAt(new Date())
            .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(expire).toInstant()))
//            .setExpiration(Date.from(ZonedDateTime.now().plusSeconds(1).toInstant()))
            .claim("sub", content)
            .signWith(SignatureAlgorithm.HS256, secretKey.getBytes("UTF-8"))
            .compact();
    }

    public String validateAndExtract(String tokenStr) throws Exception {

        String contentValue = null;

        try{
            DefaultJws defaultJws = (DefaultJws) Jwts.parser()
                .setSigningKey(secretKey.getBytes("UTF-8")).
                    parseClaimsJws(tokenStr);

                log.info(defaultJws);

                log.info(defaultJws.getBody().getClass());

                DefaultClaims claims = (DefaultClaims) defaultJws.getBody();

                log.info("--------------------------------------");

                contentValue =claims.getSubject();


        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            contentValue = null;
        }
        return contentValue;
    }

}

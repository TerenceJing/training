package com.terence.itech.base.auth;

import com.terence.itech.base.auth.entity.JsonWebToken;
import com.terence.itech.base.constants.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JJWHelper {

    private final static String KEY      = Constants.JWT_KEY;
    private final static String ISS      = Constants.JWT_ISS;
    private final static long   EXP= Constants.JWT_EXPTIME;


    public static Claims getClaims(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody();
        return claims;
    }


    public static JsonWebToken getJWTObject(String jwt) throws Exception {
        Claims jwtO = Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody();
        JsonWebToken jsonWebToken = new JsonWebToken();
        jsonWebToken.setIss(jwtO.getIssuer());
        jsonWebToken.setIat(jwtO.getIssuedAt());
        jsonWebToken.setExp(jwtO.getExpiration());
        jsonWebToken.setAud(jwtO.getAudience());
        jsonWebToken.setSub(jwtO.getSubject());
        jsonWebToken.setClaims(jwtO);
        return jsonWebToken;

    }


    public static String generateJWT(String sub, Map<String, Object> claims) throws Exception {
        Date now = new Date();
        long exp = now.getTime() + EXP;
        return generateJWT(sub, claims, null, new Date(exp));

    }


    public static String generateJWT(String sub, Map<String, Object> claims, Date nbf, Date exp) throws Exception {
        if (exp == null) {
            throw new Exception("截止日期不能为空");
        }

        Date now = new Date();
        JwtBuilder jwtBuilder = Jwts.builder();

        //此处因先设置声明，否则会将后续的声明覆盖
        if (claims != null) {
            jwtBuilder.setClaims(claims);
        }

        jwtBuilder.setSubject(sub).setIssuedAt(now).setIssuer(ISS).setExpiration(exp).signWith(SignatureAlgorithm.HS512, KEY);

        if (nbf != null) {
            jwtBuilder.setNotBefore(nbf);
        }

        return jwtBuilder.compact();
    }

    public static String generateJWT(String sub, Map<String, Object> claims, Date nbf, int minutes) throws Exception{
        Date now = new Date();
        long exp = now.getTime() + minutes * 60 * 1000;
        return generateJWT(sub, claims, nbf, new Date(exp));
    }
}

package com.ruoyi.common.core.utils;

import java.util.Map;
import com.ruoyi.common.core.constant.SecurityConstants;
import com.ruoyi.common.core.constant.TokenConstants;
import com.ruoyi.common.core.text.Convert;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT Utility Class
 *
 * @author ruoyi
 */
public class JwtUtils
{
    public static String secret = TokenConstants.SECRET;

    /**
     * Generate token from claims data
     *
     * @param claims claims data
     * @return token
     */
    public static String createToken(Map<String, Object> claims)
    {
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).compact();
        return token;
    }

    /**
     * Get claims data from token
     *
     * @param token token
     * @return claims data
     */
    public static Claims parseToken(String token)
    {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    /**
     * Get user identifier from token
     * 
     * @param token token
     * @return user ID
     */
    public static String getUserKey(String token)
    {
        Claims claims = parseToken(token);
        return getValue(claims, SecurityConstants.USER_KEY);
    }

    /**
     * Get user identifier from claims
     * 
     * @param claims identity information
     * @return user ID
     */
    public static String getUserKey(Claims claims)
    {
        return getValue(claims, SecurityConstants.USER_KEY);
    }

    /**
     * Get user ID from token
     * 
     * @param token token
     * @return user ID
     */
    public static String getUserId(String token)
    {
        Claims claims = parseToken(token);
        return getValue(claims, SecurityConstants.DETAILS_USER_ID);
    }

    /**
     * Get user ID from claims
     * 
     * @param claims identity information
     * @return user ID
     */
    public static String getUserId(Claims claims)
    {
        return getValue(claims, SecurityConstants.DETAILS_USER_ID);
    }

    /**
     * Get username from token
     * 
     * @param token token
     * @return username
     */
    public static String getUserName(String token)
    {
        Claims claims = parseToken(token);
        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
    }

    /**
     * Get username from claims
     * 
     * @param claims identity information
     * @return username
     */
    public static String getUserName(Claims claims)
    {
        return getValue(claims, SecurityConstants.DETAILS_USERNAME);
    }

    /**
     * Get value from claims by key
     * 
     * @param claims identity information
     * @param key key
     * @return value
     */
    public static String getValue(Claims claims, String key)
    {
        return Convert.toStr(claims.get(key), "");
    }
}

package com.wook.app.common.util;

import java.security.Key;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	@Value("${jwt.secret-key}") private String SECRET_KEY;
	public static final String TOKEN_PREFIX = "ROLE_";
	
	private Key getSecretKey() {
		
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

		return Keys.hmacShaKeyFor(keyBytes);
		
	} // getSecretKey() end
	
	public String getUsername(String token) {
		return getClaim(token, Claims::getSubject);
	} // extractUsername() end
	
	public Date getExpiration(String token) {
		return getClaim(token, Claims::getExpiration);
	} // extractExpiration() end
	
	private <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		
		final Claims claims = getAllClaims(token);
		
		return claimsResolver.apply(claims);
		
	} // extractClaim() end
	
	private Claims getAllClaims(String token) {
		return Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();
	} // extractAllClaims() end
	
	private Boolean isTokenExpired(String token) {
		return getExpiration(token).before(new Date());
	} // isTokenExpired() end
	
	public String createToken(UserDetails userDetails) {
		
		Map<String, Object> claims = new HashMap<String, Object>();
		
		return Jwts.builder()
					.setClaims(claims)
					.setSubject(userDetails.getUsername())
					// 토근 발급 일시
					.setIssuedAt(new Date(System.currentTimeMillis()))
					// 토근 유효 기간
					// 현재 시간 + 9시간(1000 * 60 * 60 * 9)
					.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 9))
					.signWith(getSecretKey(), SignatureAlgorithm.HS256)
					.compact();
		
	} // createToken() end
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		return getUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token); 
	} // validateToken() end
	
} // JwtUtil end
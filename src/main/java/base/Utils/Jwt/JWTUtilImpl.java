package base.Utils.Jwt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import base.Utils.Security.TokenConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Implementation of {@link IJWTUtils} with addiotional fields of security key, signature algorithm type, token time validity period in seconds and defaultTenant id. All this fields defined in application.properties
 * @author Tomasz Poławski
 *
 */
@Component
public class JWTUtilImpl implements IJWTUtil {
	
	private String signatureAlghoritm;
	
	private Long tokenValidityMinutes;
	
	private final Key key;
	
	@Autowired
	public JWTUtilImpl(@Value("${Key}") String keyValue,
			@Value("${SignatureAlghoritm}") String signatureAlghoritm,
			@Value("${TokenValidityMinutes}") Long tokenValidityMinutes
			) {
		this.signatureAlghoritm = signatureAlghoritm;
		this.tokenValidityMinutes = tokenValidityMinutes;
		this.key = new SecretKeySpec(
				keyValue.getBytes(),
				SignatureAlgorithm.forName(signatureAlghoritm).getJcaName()
				);
		
	}
	
	/**
	 * Checks if token is Signed.
	 * Note that if you are reasonably sure that the token is signed, it is more efficient to attempt toparse the token (and catching exceptions if necessary) instead of calling this method first before parsing.
	 */
	@Override
	public boolean isTokenSigned(String token) {
		return Jwts.parser().isSigned(token);
	}
	
	/**
	 * Gets user name from field Subject in body part of token.
	 * <br> While reading a token a validation is performed. If token is malformed, expired, signature is changed or not present etc. one of the exception is thrown.
	 * @throws UnsupportedJwtException if the claimsJws argument does not represent an Claims JWS
	 * @throws MalformedJwtException if the claimsJws string is not a valid JWS
	 * @throws SignatureException if the claimsJws JWS signature validation fails
	 * @throws ExpiredJwtException if the specified JWT is a Claims JWT and the Claims has an expiration timebefore the time this method is invoked.
	 * @throws IllegalArgumentException - if the claims Jws string is null or empty or only whitespace
	 */
	@Override
	public Claims getClaims(String token)
			throws ExpiredJwtException,
			UnsupportedJwtException,
			MalformedJwtException,
			SignatureException, 
			IllegalArgumentException {
		return Jwts
				.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
	}
	
	/**
	 * Gets user name from field Subject in body part of token. If field is not present then null returned.
	 * <br> While reading a token a validation is performed. If token is malformed, expired, signature is changed or not present etc. one of the exception is thrown.
	 * @throws UnsupportedJwtException if the claimsJws argument does not represent an Claims JWS
	 * @throws MalformedJwtException if the claimsJws string is not a valid JWS
	 * @throws SignatureException if the claimsJws JWS signature validation fails
	 * @throws ExpiredJwtException if the specified JWT is a Claims JWT and the Claims has an expiration time before the time this method is invoked.
	 * @throws IllegalArgumentException - if the claims Jws string is null or empty or only whitespace
	 */
	@Override
	public String getUsername(String token)
			throws ExpiredJwtException,
			UnsupportedJwtException,
			MalformedJwtException,
			SignatureException, 
			IllegalArgumentException {
		return Jwts
				.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}
	
	/**
	 * Gets Id from Jti field in body part of token. if field is not present then null returned.
	 * <br> While reading a token a validation is performed. If token is malformed, expired, signature is changed or not present etc. one of the exception is thrown.
	 * @throws UnsupportedJwtException if the claimsJws argument does not represent an Claims JWS
	 * @throws MalformedJwtException if the claimsJws string is not a valid JWS
	 * @throws SignatureException if the claimsJws JWS signature validation fails
	 * @throws ExpiredJwtException if the specified JWT is a Claims JWT and the Claims has an expiration time before the time this method is invoked.
	 * @throws IllegalArgumentException - if the claims Jws string is null or empty or only whitespace
	 */
	@Override
	public String getId(String token) 
			throws ExpiredJwtException,
			UnsupportedJwtException,
			MalformedJwtException,
			SignatureException, 
			IllegalArgumentException {
		return Jwts
				.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody()
				.getId();
	}
	
	/**
	 * Gets tenant Id from tenantId field in body part of token. if field is not present then null returned.
	 * <br> While reading a token a validation is performed. If token is malformed, expired, signature is changed or not present etc. one of the exception is thrown.
	 * @throws UnsupportedJwtException if the claimsJws argument does not represent an Claims JWS
	 * @throws MalformedJwtException if the claimsJws string is not a valid JWS
	 * @throws SignatureException if the claimsJws JWS signature validation fails
	 * @throws ExpiredJwtException if the specified JWT is a Claims JWT and the Claims has an expiration time before the time this method is invoked.
	 * @throws IllegalArgumentException - if the claims Jws string is null or empty or only whitespace
	 */
	@Override
	public String getTenant(String token) 
			throws ExpiredJwtException,
			UnsupportedJwtException,
			MalformedJwtException,
			SignatureException, 
			IllegalArgumentException  {
		return Jwts
				.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody().getOrDefault("tenantId", "defaultTenant").toString();
	}
	
	/**
	 * GeneratesToken with default tenantId defined in application.properties and user from class implementing {@link UserDetails}.
	 * @throws NullPointerException if user is null.
	 */
	@Override
	public String generateToken(UserDetails user)
			throws InvalidKeyException,
			NullPointerException {
		return generateToken(user, TokenConstant.EMPTY_TOKEN_TENANT_FIELD.getValue());
	}
	
	/**
	 * GeneratesToken with user from class implementing {@link UserDetails}. if tenantId is null than default value defined in application.properties is set for this key.
	 * @throws NullPointerException if user is null.
	 */
	@Override
	public String generateToken(UserDetails user, String tenantId)
			throws InvalidKeyException,
			NullPointerException {
		if (user==null) throw new NullPointerException("User can't be null");
		if(tenantId==null||tenantId.isBlank()) tenantId=TokenConstant.EMPTY_TOKEN_TENANT_FIELD.getValue();
		return
				Jwts.builder()
				.setSubject(user.getUsername())
				.setId(getNewUUID())
				.claim("tenantId", tenantId)
				.setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(Instant.now().plusSeconds(tokenValidityMinutes)))
				.signWith(SignatureAlgorithm.forName(signatureAlghoritm),key).compact();
	}
	
	/**
	 *  GeneratesToken from predefined {@link Claims}
	 * @throws NullPointerException if claims is null.
	 */
	@Override
	public String generateToken(Claims claims)
			throws InvalidKeyException,
			NullPointerException {
		if (claims==null) throw new NullPointerException("Claims can't be null");
		if(claims.get("tenantId")==null||claims.get("tenantId").toString().isBlank()) claims.put("tenantId",TokenConstant.EMPTY_TOKEN_TENANT_FIELD.getValue());
		return
				Jwts.builder().setClaims(claims)
				.setId(getNewUUID())
				.setIssuedAt(Date.from(Instant.now()))
				.setExpiration(Date.from(Instant.now().plusSeconds(tokenValidityMinutes)))
				.signWith(SignatureAlgorithm.forName(signatureAlghoritm),key).compact();
	}	
	
	
	/**
	 * @throws UnsupportedJwtException if the claimsJws argument does not represent an Claims JWS
	 * @throws MalformedJwtException if the claimsJws string is not a valid JWS
	 * @throws SignatureException if the claimsJws JWS signature validation fails
	 * @throws ExpiredJwtException if the specified JWT is a Claims JWT and the Claims has an expiration time before the time this method is invoked.
	 * @throws IllegalArgumentException - if the token string is null or empty or only whitespace
	 */
	public String refreshTokenAtHalfTime(String token)
						throws InvalidKeyException {
			if (token==null ||token.isBlank()) throw new NullPointerException("Claims can't be null");
		
		Claims claims=this.getClaims(token);
		
		if (claims.getExpiration().getTime() - Date.from(Instant.now()).getTime() > 
		(Date.from(Instant.now()).getTime()-claims.getIssuedAt().getTime())/2) return token;
		claims.setIssuedAt(Date.from(Instant.now()));
		claims.setExpiration(Date.from(Instant.now().plusSeconds(tokenValidityMinutes)));
		
		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.forName(signatureAlghoritm),key).compact();
	}

	private String getNewUUID() {
		return UUID.randomUUID().toString();
	}
	
	
	public static void main(String args[]) {
			
	}
}

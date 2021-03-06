package base.Utils.Jwt;

import java.security.InvalidKeyException;

import org.springframework.security.core.userdetails.UserDetails;

import base.Model.baza.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * This interface adds an utility for JWT (Json Web Token) in form of signed token (JWS). Declares most method's for implementation token with multi-tenant database.
 * 
 * @author Tomasz Poławski
 *
 */
public interface IJWTUtil {

	/**
	 * Returns {@link Claims} from body part of token
	 * @param token as String object.
	 * @return {@link Claims}
	 */
	Claims getClaims(String token);
	
	/**
	 * Gets user name from field Subject in body part of token
	 * @param token as String object.
	 * @return String
	 */
	String getUsername(String token);
	
	/**
	 * gets id from field jti in body part of token
	 * @param token as String object.
	 * @return String
	 */
	String getId(String token);

	/**
	 * Gets tenant Id from tenantId field in body part of token.
	 * @param token as String object.
	 * @return String
	 */
	String getTenant(String token);

	/**
	 * GeneratesToken with default tenantId and user from class implementing UserDetails
	 * @param user - {@link UserDetails} implementation.
	 * @return String - generated token
	 * @throws InvalidKeyException if the signing  {@link Key} isn't valid for exact signing algorithm
	 * @see {@link #generateToken(UserDetails, String)}
	 * @see {@link #generateToken(Claims)}
	 */
	String generateToken(UserDetails user) throws InvalidKeyException;

	/**
	 * GeneratesToken with user defined tenantId and user from class implementing UserDetails
	 * @param user - {@link UserDetails} implementation
	 * @param tenantId String.
	 * @return String - generated token
	 * @throws InvalidKeyException if the signing {@link Key} isn't valid for exact signing algorithm
	 * @see {@link #generateToken(UserDetails)}
	 * @see {@link #generateToken(Claims)}	
	 */
	String generateToken(UserDetails user, String tenantId) throws InvalidKeyException;

	/**
	 * GeneratesToken from predefined {@link Claims}
	 * @param claims - {@link Claims}
	 * @return String - generated token
	 * @throws InvalidKeyException if the signing {@link Key} isn't valid for exact signing algorithm
	 * @see {@link #generateToken(UserDetails, String)}
	 * @see {@link #generateToken(UserDetails)}	 
	 */
	String generateToken(Claims claims) throws InvalidKeyException;
	
	/**
	 * Checks if token is Signed
	 * @param token as String field
	 * @return True if signed, opposite false.
	 */
	boolean isTokenSigned(String token);
	
	/**
	 * Refreshes token when there have passed half of token time.
	 * @param token - String
	 * @return token - new one - String
	 * @throws NullPointerException  when token is null
	 * @throws InvalidKeyException when key for signing is invalid
	 */
	String refreshTokenAtHalfTime(String token) throws InvalidKeyException, NullPointerException;
	
	
	/**
	 * GeneratesToken with Subject as user ID from {@link UserDetails} .
	 * @throws NullPointerException if user is null.
	 */
	String generateEmailActivationToken(Users user) throws InvalidKeyException, NullPointerException;

	/**
	 * Gets email activation claims from token.
	 * <br> While reading a token a validation is performed. If token is malformed, expired, signature is changed or not present etc. one of the exception is thrown.
	 * @throws UnsupportedJwtException if the claimsJws argument does not represent an Claims JWS
	 * @throws MalformedJwtException if the claimsJws string is not a valid JWS
	 * @throws SignatureException if the claimsJws JWS signature validation fails
	 * @throws ExpiredJwtException if the specified JWT is a Claims JWT and the Claims has an expiration timebefore the time this method is invoked.
	 * @throws IllegalArgumentException - if the claims Jws string is null or empty or only whitespace
	 */
	Claims getEmailActivationClaims(String token) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException;

}
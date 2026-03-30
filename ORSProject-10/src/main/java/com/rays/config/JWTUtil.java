package com.rays.config;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Utility class for generating, validating, and parsing custom HS256 JWT tokens.
 * Secret and expiration are injected from application properties
 * ({@code jwt.secret}, {@code jwt.expiration}).
 *
 * @author Ajay Pratap Kerketta
 */
@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private long jwtExpiration; // in milliseconds

	private static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Generates a signed HS256 JWT token containing userId, loginId, role, iat, and exp claims.
	 *
	 * @param userId  the user's primary key
	 * @param loginId the user's login identifier (used as JWT subject)
	 * @param role    the user's role
	 * @return a dot-separated JWT string ({@code header.payload.signature})
	 * @throws Exception if JSON serialization or HMAC signing fails
	 */
	public String generateToken(Long userId, String loginId, String role) throws Exception {
		long nowMillis = System.currentTimeMillis();
		long expMillis = nowMillis + jwtExpiration;

		// JWT Header
		Map<String, Object> header = new HashMap<>();
		header.put("alg", "HS256");
		header.put("typ", "JWT");

		// JWT Payload (claims)
		Map<String, Object> payload = new HashMap<>();
		payload.put("sub", loginId); // loginId as subject
		payload.put("userId", userId); // numeric user id
		payload.put("role", role); // user role
		payload.put("iat", nowMillis); // issued at
		payload.put("exp", expMillis); // expiration

		String headerBase64 = encodeUrl(objectMapper.writeValueAsString(header));
		String payloadBase64 = encodeUrl(objectMapper.writeValueAsString(payload));
		String signatureBase64 = sign(headerBase64 + "." + payloadBase64, jwtSecret);

		return String.join(".", headerBase64, payloadBase64, signatureBase64);
	}

	/**
	 * Validates a JWT token by verifying its structure, signature, subject, and expiry.
	 *
	 * @param token           the JWT token string to validate
	 * @param expectedLoginId the login ID expected to match the token's {@code sub} claim
	 * @return {@code true} if the token is valid
	 * @throws Exception if the token is malformed, the signature mismatches, the subject
	 *                   doesn't match, or the token has expired
	 */
	public boolean validateToken(String token, String expectedLoginId) throws Exception {
		String[] parts = token.split("\\.");
		if (parts.length != 3) {
			throw new Exception("Invalid JWT token");
		}

		String payloadJson = decode(parts[1]);
		String tokenLoginId = extractField(payloadJson, "sub");
		String expectedSignature = sign(parts[0] + "." + parts[1], jwtSecret);

		if (!expectedSignature.equals(parts[2])) {
			throw new Exception("JWT signature does not match");
		}

		if (!expectedLoginId.equals(tokenLoginId)) {
			throw new Exception("JWT subject (loginId) does not match");
		}

		if (isTokenExpired(payloadJson)) {
			throw new Exception("JWT token has expired");
		}

		return true;
	}

	/**
	 * Extracts the {@code sub} (loginId) claim from the token payload.
	 *
	 * @param token the JWT token string
	 * @return the loginId stored in the {@code sub} claim
	 */
	public String extractLoginId(String token) {
		return extractField(decode(token.split("\\.")[1]), "sub");
	}

	/**
	 * Extracts the {@code userId} claim from the token payload.
	 *
	 * @param token the JWT token string
	 * @return the user ID as a {@link Long}
	 */
	public Long extractUserId(String token) {
		return Long.parseLong(extractField(decode(token.split("\\.")[1]), "userId"));
	}

	/**
	 * Extracts the {@code role} claim from the token payload.
	 *
	 * @param token the JWT token string
	 * @return the role string stored in the token
	 */
	public String extractRole(String token) {
		return extractField(decode(token.split("\\.")[1]), "role");
	}

	// -------------------------
	// Helper methods
	// -------------------------

	/**
	 * Checks whether the token's {@code exp} claim is in the past.
	 *
	 * @param payloadJson the decoded JSON payload string
	 * @return {@code true} if the token is expired; {@code false} otherwise
	 */
	private boolean isTokenExpired(String payloadJson) {
		long exp = Long.parseLong(extractField(payloadJson, "exp"));
		return exp < (System.currentTimeMillis() / 1000);
	}

	/**
	 * Reads a single field value from a JSON string using Jackson.
	 *
	 * @param json  the JSON string to parse
	 * @param field the key to look up
	 * @return the field value as a {@link String}, or {@code null} if parsing fails
	 */
	private String extractField(String json, String field) {
		try {
			Map<String, Object> map = objectMapper.readValue(json, Map.class);
			return String.valueOf(map.get(field));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Computes the HMAC-SHA256 signature of the given data using the provided key.
	 *
	 * @param data the input string to sign
	 * @param key  the secret key string
	 * @return the Base64URL-encoded signature
	 * @throws Exception if the HMAC algorithm is unavailable or key initialization fails
	 */
	private String sign(String data, String key) throws Exception {
		Mac mac = Mac.getInstance("HmacSHA256");
		mac.init(new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
		return encodeUrl(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
	}

	/**
	 * Base64URL-encodes a plain string (without padding).
	 *
	 * @param data the string to encode
	 * @return the Base64URL-encoded string
	 */
	private String encodeUrl(String data) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(data.getBytes(StandardCharsets.UTF_8));
	}

	/**
	 * Base64URL-encodes a byte array (without padding).
	 *
	 * @param data the byte array to encode
	 * @return the Base64URL-encoded string
	 */
	private String encodeUrl(byte[] data) {
		return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
	}

	/**
	 * Decodes a Base64URL-encoded string to a UTF-8 string.
	 *
	 * @param data the Base64URL-encoded string to decode
	 * @return the decoded UTF-8 string
	 */
	private String decode(String data) {
		return new String(Base64.getUrlDecoder().decode(data), StandardCharsets.UTF_8);
	}
}
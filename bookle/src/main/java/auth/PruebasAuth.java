package auth;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class PruebasAuth {

	public static void main(String[] args) {
		
		// Generación de un token
		
		Map<String, Object> claims = new HashMap<String, Object>(); // el cuerpo del token

		claims.put("sub", "Juan");

		Date caducidad = Date.from(Instant.now().plusSeconds(1)); // 1 hora de validez
		
		String token = Jwts.builder()
				.setClaims(claims).signWith(SignatureAlgorithm.HS256, "secreto")
				.setExpiration(caducidad).compact();
		
		System.out.println(token);
		
		 Claims claims2 = Jwts.parser()
				 .setSigningKey("secreto")
				 .parseClaimsJws(token)
				 .getBody();
		 
		 System.out.println(claims2.getSubject());
	}
}

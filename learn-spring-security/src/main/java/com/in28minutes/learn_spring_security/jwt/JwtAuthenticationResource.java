package com.in28minutes.learn_spring_security.jwt;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class JwtAuthenticationResource {
	
	private JwtEncoder jwtEncoder;
	
	public JwtAuthenticationResource(JwtEncoder jwtEncoder) {
		this.jwtEncoder=jwtEncoder;
	}
	@PostMapping("/authenticate")
	public JwtResponse authenticate(Authentication authentication) {
		return new JwtResponse(createToke(authentication));
	}
	private String createToke(Authentication authentication) {
		var claims = JwtClaimsSet.builder()
						.issuer("self")
						.issuedAt(Instant.now())
						.expiresAt(Instant.now().plusSeconds(60*30))
						.subject(authentication.getName())
						.claim("scope",creatScope(authentication))
						.build();
					
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
	private Object creatScope(Authentication authentication) {
		return authentication.getAuthorities().stream()
			.map(a->a.getAuthority())
			.collect(Collectors.joining(" "));
	}
	
}

record JwtResponse(String token) {
	
}

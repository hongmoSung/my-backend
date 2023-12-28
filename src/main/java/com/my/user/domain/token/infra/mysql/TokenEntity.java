package com.my.user.domain.token.infra.mysql;

import com.my.user.domain.token.domain.Token;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "token")
public class TokenEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private UUID userUuid;

	private String tokenString;

	@Enumerated(EnumType.STRING)
	private Token.Status status;

	private Long expireAt;

	@Builder
	public TokenEntity(UUID userUuid, String tokenString, Token.Status status, Long expireAt) {
		this.userUuid = userUuid;
		this.tokenString = tokenString;
		this.status = status;
		this.expireAt = expireAt;
	}

}

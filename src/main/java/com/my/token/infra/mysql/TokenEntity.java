package com.my.token.infra.mysql;

import com.my.token.domain.Token;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
	private Token.Status status = Token.Status.WAIT;

	private LocalDateTime expireAt;

	private LocalDateTime createAt;

	@Builder
	public TokenEntity(UUID userUuid, String tokenString, Token.Status status, Long expireAt) {
		this.userUuid = userUuid;
		this.tokenString = tokenString;
		this.status = status;
		this.expireAt = LocalDateTime.now();
		this.createAt = LocalDateTime.now();
	}

	public void done() {
		this.status = Token.Status.DONE;
	}

	public void changToWork() {
		this.status = Token.Status.WORK;
		this.expireAt = LocalDateTime.now().plusMinutes(30);
	}

}

package com.my.user.domain.token.domain;

import com.my.user.domain.token.infra.mysql.TokenEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@Getter
public class Token {

	public enum Status {

		VALID, STOP

	}

	private String userUuid;

	private String email;

	private int number;

	private Long expireAt;

	private Status status = Status.VALID;

	private String tokenString = "";

	public Token(String userUuid, String email) {
		this.userUuid = userUuid;
		this.email = email;
	}

	@Builder
	public Token(String userUuid, String email, String tokenString) {
		this(userUuid, email);
		this.tokenString = tokenString;
		this.expireAt = LocalDateTime.now().plusMinutes(5).toEpochSecond(ZoneOffset.UTC);
	}

	public void addTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

	public TokenEntity toEntity() {
		return TokenEntity.builder()
			.userUuid(UUID.fromString(this.userUuid))
			.tokenString(this.tokenString)
			.status(this.status)
			.expireAt(this.expireAt)
			.build();
	}

}

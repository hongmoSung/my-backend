package com.my.token.domain;

import com.my.token.infra.mysql.TokenEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Token {

	public enum Status {

		WAIT, WORK, DONE;

	}

	private String userUuid;

	private String email;

	private Status status = Status.WAIT;

	private String tokenString = "";

	public Token(String userUuid, String email) {
		this.userUuid = userUuid;
		this.email = email;
	}

	@Builder
	public Token(String userUuid, String email, String tokenString) {
		this(userUuid, email);
		this.tokenString = tokenString;
	}

	public void addTokenString(String tokenString) {
		this.tokenString = tokenString;
	}

	public TokenEntity toEntity() {
		return TokenEntity.builder()
			.userUuid(UUID.fromString(this.userUuid))
			.tokenString(this.tokenString)
			.status(this.status)
			.build();
	}

	public void changeToWork() {
		this.status = Status.WORK;
	}

	public void changeDone() {
		this.status = Status.DONE;
	}

}

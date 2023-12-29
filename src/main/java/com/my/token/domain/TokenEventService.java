package com.my.token.domain;

import com.my.token.infra.mysql.TokenEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TokenEventService {

	private final TokenRepo tokenRepo;

	@Transactional
	@EventListener(classes = { PayEndEvent.class })
	public void payEndEventHandler() {
		Optional<TokenEntity> optionalToken = tokenRepo.findFirstWaitingToken();
		if (optionalToken.isPresent()) {
			TokenEntity tokenEntity = optionalToken.get();
			tokenEntity.changToWork();
		}
	}

}

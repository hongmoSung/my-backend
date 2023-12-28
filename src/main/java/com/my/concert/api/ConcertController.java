package com.my.concert.api;

import com.my.concert.api.dto.ReqCreateConcertDto;
import com.my.concert.app.ConcertFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class ConcertController {

	private final ConcertFacade concertFacade;

	@PostMapping("/concerts")
	public ResponseEntity<Void> createConcert(@RequestBody ReqCreateConcertDto dto) {
		concertFacade.createThingsNeededForTheConcert(dto);
		return ResponseEntity.created(null).build();
	}

}

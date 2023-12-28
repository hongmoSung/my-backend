package com.my.concert.domain.seat.api.dto;

import lombok.Getter;

@Getter
public class ResEnableSeat {

	private Long id;

	private int no;

	public ResEnableSeat(Long id, int no) {
		this.id = id;
		this.no = no;
	}

}

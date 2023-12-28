package com.my.concert.domain.seat.infra.mysql;

import com.my.concert.infra.mysql.ConcertEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "seat")
public class SeatEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int no;

	private LocalDate date;

	private boolean isReserved = false;

	private int price = 5000;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "concertId", foreignKey = @ForeignKey(name = "FK_Seat_Concert_Id"))
	private ConcertEntity concert;

	@Builder
	public SeatEntity(ConcertEntity concert, int no, LocalDate date) {
		this.concert = concert;
		this.no = no;
		this.date = date;
	}

	public void addConcert(ConcertEntity concert) {
		this.concert = concert;
	}

	public void reserveSeat() {
		this.isReserved = true;
	}

}

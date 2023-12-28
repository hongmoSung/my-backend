package com.my.concert.domain.booking.infra.mysql;

import com.my.concert.domain.seat.infra.mysql.SeatEntity;
import com.my.user.infra.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "booking")
public class BookingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private LocalDate date;

	private boolean isPaid = false;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", foreignKey = @ForeignKey(name = "FK_Booking_Concert_Id"))
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seatId", foreignKey = @ForeignKey(name = "FK_Booking_Seat_Id"))
	private SeatEntity seat;

	@Builder
	public BookingEntity(LocalDate date, UserEntity user, SeatEntity seat) {
		this.date = date;
		this.user = user;
		this.seat = seat;
	}

	public void paid() {
		this.isPaid = true;
	}

}

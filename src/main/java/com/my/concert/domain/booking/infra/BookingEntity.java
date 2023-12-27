package com.my.concert.domain.booking.infra;

import com.my.concert.infra.mysql.ConcertEntity;
import com.my.concert.domain.seat.infra.mysql.SeatEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Booking")
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(
            name = "concertId",
            foreignKey = @ForeignKey(name = "FK_Booking_Concert_Id")
    )
    private ConcertEntity concert;

    @ManyToOne
    @JoinColumn(
            name = "seatId",
            foreignKey = @ForeignKey(name = "FK_Booking_Seat_Id")
    )
    private SeatEntity seat;

    @Builder
    public BookingEntity(LocalDate date, ConcertEntity concert, SeatEntity seat) {
        this.date = date;
        this.concert = concert;
        this.seat = seat;
    }

    public void addConcert(ConcertEntity concert) {
        this.concert = concert;
    }
}

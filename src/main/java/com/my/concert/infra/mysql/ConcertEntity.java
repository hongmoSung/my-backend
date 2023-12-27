package com.my.concert.infra.mysql;

import com.my.concert.domain.booking.infra.BookingEntity;
import com.my.concert.domain.seat.infra.mysql.SeatEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "concert")
public class ConcertEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @OneToMany(
        mappedBy = "concert",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<SeatEntity> seats;

    @OneToMany(
        mappedBy = "concert",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<BookingEntity> bookings;

    @Builder
    public ConcertEntity(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addSeatsAndBookings(List<SeatEntity> seats, List<BookingEntity> bookings) {
        this.seats = seats;
        seats.forEach(seatEntity -> seatEntity.addConcert(this));
        this.bookings = bookings;
        bookings.forEach(booking -> booking.addConcert(this));
    }
}

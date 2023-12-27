package com.my.concert.infra.mysql;

import com.my.concert.domain.seat.infra.mysql.SeatEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    @Builder
    public ConcertEntity(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addSeats(List<SeatEntity> seats) {
        this.seats = seats;
        seats.forEach(seatEntity -> seatEntity.addConcert(this));
    }
}

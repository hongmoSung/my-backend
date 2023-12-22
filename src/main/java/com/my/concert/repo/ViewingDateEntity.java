package com.my.concert.repo;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "viewingDate")
public class ViewingDateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private int capacity = 50;

    @ManyToOne
    @JoinColumn(
            name="concertId",
            foreignKey = @ForeignKey(name = "FK_Concert_Id")
    )
    private ConcertEntity concert;

    @OneToMany(mappedBy = "viewingDate", cascade = CascadeType.ALL)
    private List<SeatEntity> seats;

    @Builder
    public ViewingDateEntity(LocalDate date, int capacity, ConcertEntity concert) {
        this.date = date;
        this.capacity = capacity;
        this.concert = concert;
    }

    public void addSeats(List<SeatEntity> seats) {
        this.seats = seats;
    }
}

package com.komsije.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDate startDate;
    @Column(nullable = false)
    private LocalDate dateCreated;
    @Column(nullable = false)
    private int days;
    @Column
    private Integer numberOfGuests;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private Long hostId;
    @Column(nullable = false)
    private Long guestId;
    @ManyToOne
    private Accommodation accommodation;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;
}

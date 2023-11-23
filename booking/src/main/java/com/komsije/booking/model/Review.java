package com.komsije.booking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int grade;
    @Column(nullable = true)
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    private Account author;
    @Column(nullable = false)
    private boolean isApproved;
}
package com.komsije.booking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
@Getter
@Setter
@NoArgsConstructor
public class Guest extends Account {
    private String firstName;
    private String lastName;
    private Account account;
    private String phone;
    private int timesCancelled;
    private HashSet<Accommodation> favorites;
}

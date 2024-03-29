package com.komsije.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponse {
    private boolean isAvailable;
    private double pricePerNight;
    private double totalPrice;
}

package com.komsije.booking.mapper;

import com.komsije.booking.dto.AccommodationDto;
import com.komsije.booking.dto.ReservationDto;
import com.komsije.booking.dto.ReservationViewDto;
import com.komsije.booking.model.Accommodation;
import com.komsije.booking.model.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses={GuestMapper.class} , nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public abstract class ReservationMapper {
    public ReservationDto toDto(Reservation reservation){
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setStartDate(reservation.getStartDate());
        reservationDto.setDays(reservation.getDays());
        reservationDto.setReservationStatus(reservation.getReservationStatus());
        reservationDto.setAccommodationId(reservation.getAccommodation().getId());

        return reservationDto;
    }
    public abstract Reservation fromDto(ReservationDto reservationDto);
    public List<ReservationDto> toDto(List<Reservation> reservationList){
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation:
             reservationList) {
            reservationDtos.add(toDto(reservation));
        }
        return reservationDtos;
    }
    public abstract void update(@MappingTarget Reservation reservation, ReservationDto reservationDto);

    public ReservationViewDto toViewDto(Reservation reservation){
        ReservationViewDto reservationDto = new ReservationViewDto();
        reservationDto.setId(reservation.getId());
        reservationDto.setStartDate(reservation.getStartDate());
        reservationDto.setEndDate(reservation.getStartDate().plusDays(reservation.getDays()));
        reservationDto.setPrice(reservation.getPrice());
        Accommodation accommodation = reservation.getAccommodation();
        reservationDto.setAccommodationName(accommodation.getName()+" , "+ accommodation.getAddress().getCity());
        reservationDto.setGuestEmail(reservation.getGuest().getEmail());
        reservationDto.setHostEmail(reservation.getHost().getEmail());
        reservationDto.setReservationStatus(reservation.getReservationStatus());
        reservationDto.setNumberOfGuests(reservation.getNumberOfGuests());
        return reservationDto;
    }
    public List<ReservationViewDto> toViewDto(List<Reservation> reservationList){
        List<ReservationViewDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation:
                reservationList) {
            reservationDtos.add(toViewDto(reservation));
        }
        return reservationDtos;
    }
}

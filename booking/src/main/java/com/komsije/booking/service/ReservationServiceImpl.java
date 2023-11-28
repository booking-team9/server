package com.komsije.booking.service;

import com.komsije.booking.dto.ReservationDto;
import com.komsije.booking.mapper.ReservationMapper;
import com.komsije.booking.model.Reservation;
import com.komsije.booking.model.ReservationStatus;
import com.komsije.booking.repository.ReservationRepository;
import com.komsije.booking.service.interfaces.ReportService;
import com.komsije.booking.service.interfaces.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper mapper;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public ReservationDto findById(Long id) {
        return mapper.toDto(reservationRepository.findById(id).orElseGet(null));
    }

    public List<ReservationDto> findAll() {
        return mapper.toDto(reservationRepository.findAll());
    }

    public List<ReservationDto> getByReservationStatus(ReservationStatus reservationStatus){return mapper.toDto(reservationRepository.findReservationsByReservationStatus(reservationStatus));}

    @Override
    public boolean hasActiveReservations(Long accountId) {
        List<Reservation> reservations = reservationRepository.findAll();
        for (Reservation reservation: reservations){
            if (reservation.getGuest().getId().equals(accountId) && reservation.getReservationStatus().equals(ReservationStatus.Active)){
                return true;
            }
        }
        return false;
    }
    /*public static boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return start1.before(end2) && start2.before(end1);
    }*/
    @Override
    public boolean hasOverlappingReservations(Date startDate, Date endDate) {
      /* List<Reservation> reservations = reservationRepository.findReservationsByReservationStatus(ReservationStatus.Active);
       for(Reservation reservation: reservations){
           if (startDate.before(reservation.getStartDate().))
       }*/
        return false;
    }


    public ReservationDto save(ReservationDto reservationDto) {
        reservationRepository.save(mapper.fromDto(reservationDto));
        return reservationDto;
    }

    @Override
    public ReservationDto update(ReservationDto reservationDto) {
        Reservation reservation = reservationRepository.findById(reservationDto.getId()).orElseGet(null);
        if (reservation == null){
            return null;
        }
        mapper.update(reservation, reservationDto);
        reservationRepository.save(reservation);
        return reservationDto;
    }

    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }
}

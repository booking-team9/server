package com.komsije.booking.controller;

import com.komsije.booking.dto.AccommodationDto;
import com.komsije.booking.model.Accommodation;
import com.komsije.booking.model.AccommodationType;
import com.komsije.booking.service.AccommodationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/accommodations")
public class AccommodationController {
    private final AccommodationServiceImpl accommodationService;
    @Autowired
    public AccommodationController(AccommodationServiceImpl accommodationService) {
        this.accommodationService = accommodationService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<AccommodationDto>> getAllAccommodations(){
        List<Accommodation> accommodations = accommodationService.findAll();

        List<AccommodationDto> accommodationDtos = new ArrayList<>();
        for (Accommodation accommodation : accommodations) {
            accommodationDtos.add(new AccommodationDto(accommodation));
        }
        return new ResponseEntity<>(accommodationDtos, HttpStatus.OK);

    }
    @GetMapping(value = "/type")
    public ResponseEntity<List<AccommodationDto>> getByAccommodationType(@RequestParam String type){
        try{
            List<Accommodation> accommodations = accommodationService.getByAccommodationType(AccommodationType.valueOf(type));

            List<AccommodationDto> accommodationDtos = new ArrayList<>();
            for (Accommodation accommodation : accommodations) {
                accommodationDtos.add(new AccommodationDto(accommodation));
            }
            return new ResponseEntity<>(accommodationDtos, HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AccommodationDto> getAccommodation(@PathVariable Long id) {

        Accommodation accommodation = accommodationService.findOne(id);

        if (accommodation == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new AccommodationDto(accommodation), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<AccommodationDto> saveAccommodation(@RequestBody AccommodationDto accommodationDTO) {

        Accommodation accommodation = new Accommodation();
        accommodation.setName(accommodationDTO.getName());
        accommodation.setDescription(accommodationDTO.getDescription());
        accommodation.setAddress(accommodationDTO.getAddress());
        accommodation.setAccommodationType(accommodationDTO.getAccommodationType());
        accommodation.setAmenities(accommodationDTO.getAmenities());
        accommodation.setMaxGuests(accommodationDTO.getMaxGuests());
        accommodation.setMinGuests(accommodationDTO.getMinGuests());
        accommodation.setPhotos(accommodationDTO.getPhotos());
        accommodation.setPricePerGuest(accommodationDTO.isPricePerGuest());
        accommodation.setCancellationDeadline(accommodationDTO.getCancellationDeadline());
        accommodation.setAutoApproval(true);
        accommodation.setAverageGrade(accommodationDTO.getAverageGrade());
        accommodation.setAvailability(accommodationDTO.getAvailability());

        accommodation = accommodationService.save(accommodation);
        return new ResponseEntity<>(new AccommodationDto(accommodation), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAccommodation(@PathVariable Long id) {

        Accommodation accommodation = accommodationService.findOne(id);

        if (accommodation != null) {
            accommodationService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

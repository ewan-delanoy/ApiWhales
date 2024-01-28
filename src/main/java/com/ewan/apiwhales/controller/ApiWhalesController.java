package com.ewan.apiwhales.controller;



import com.ewan.apiwhales.enumeration.StatutEnum;
import com.ewan.apiwhales.input.ReservationInput;
import com.ewan.apiwhales.output.ReservationOutput;
import com.ewan.apiwhales.service.ApiWhalesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApiWhalesController {

    public ApiWhalesService apiWhalesService;

    public ApiWhalesController(ApiWhalesService apiWhalesService) {
        this.apiWhalesService = apiWhalesService;
    }

    @PostMapping("/reservation")
    public ResponseEntity<Long> createReservation(@RequestBody ReservationInput reservationInput) {
        Long newReservationId = apiWhalesService.effectuerReservation(reservationInput);
        return new ResponseEntity<Long>(newReservationId, HttpStatus.CREATED);
    }

    @GetMapping("/reservations/{id}")
    public @ResponseBody List<ReservationOutput> reservationsClient(@PathVariable Long id) {
        String acceptee = StatutEnum.ACCEPTEE.getNom();
        List<ReservationOutput> reservations = apiWhalesService.reservationsClient(id, acceptee);
        return reservations;
        // return new ResponseEntity<List<ReservationOutput>>(reservations, HttpStatus.FOUND);
    }

}

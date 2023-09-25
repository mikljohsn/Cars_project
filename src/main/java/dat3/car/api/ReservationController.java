package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Reservation;
import dat3.car.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    ReservationResponse makeReservation(@RequestBody ReservationRequest body){
        return reservationService.reserveCar(body);
    }

    //ADMIN
    @GetMapping("/{username}")
    public List<ReservationResponse> getReservationsForUser(@PathVariable String username){
        return reservationService.getReservationsForUser(username);
    }
}

package dat3.car.api;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Reservation;
import dat3.car.service.ReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    ReservationResponse makeReservation(@RequestBody ReservationRequest res){
        return reservationService.reserveCar(res);
    }
    @PostMapping("/v2")
    ReservationResponse makeReservationV2(@RequestBody ReservationRequest res, Principal principal){ //ReservationResponse makeReservationV2(@PathVariable int id, @PathVariable String date, Principal principal)
        res.setUsername("");
        res.setUsername(principal.getName()); //bruger fra token
        return reservationService.reserveCar(res);
    }

    //ADMIN
    @GetMapping("/{username}")
    public List<ReservationResponse> getReservationsForUser(@PathVariable String username){
        return reservationService.getReservationsForUser(username);
    }
    // The endpoint that allows an authenticated user to see his reservations
    @GetMapping("/reservations-for-authenticated")
    public List<ReservationResponse> getReservationsForUser(Principal principal){
        return reservationService.getReservationsForUser(principal.getName());
    }

}

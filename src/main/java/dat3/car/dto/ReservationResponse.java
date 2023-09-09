package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Reservation;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReservationResponse {
    private int reservationId;
    private int carId;
    private String carBrand;
    private String carModel;
    private double price;
    private LocalDate reservationDate;

    public ReservationResponse(Reservation reservation) {
        this.reservationId = reservation.getId();
        this.carId = reservation.getCar().getId();
        this.carBrand = reservation.getCar().getBrand();
        this.carModel = reservation.getCar().getModel();
        this.price = reservation.getCar().getPricePrDay();
        this.reservationDate = reservation.getRentalDate();
    }
}

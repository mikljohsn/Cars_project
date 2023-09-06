package dat3.car.dto;

import dat3.car.entity.Reservation;

import java.time.LocalDate;

public class ReservationResponse {
    private int reservationId;
    private int carId;
    private String carBrand;
    private String carModel;
    private LocalDate reservationDate;

    public ReservationResponse(Reservation reservation) {
        this.reservationId = reservation.getId();
        this.carId = reservation.getCar().getId();
        this.carBrand = reservation.getCar().getBrand();
        this.carModel = reservation.getCar().getModel();
        this.reservationDate = reservation.getRentalDate();
    }
}

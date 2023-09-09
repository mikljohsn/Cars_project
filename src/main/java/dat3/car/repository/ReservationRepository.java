package dat3.car.repository;

import dat3.car.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    boolean existsByCar_IdAndRentalDate(int carId, LocalDate date);
}

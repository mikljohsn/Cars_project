package dat3.car.repository;

import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findCarsByBrandAndModel(String brand, String model);

}

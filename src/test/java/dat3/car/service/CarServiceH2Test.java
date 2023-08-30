package dat3.car.service;

import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CarServiceH2Test {
    @Autowired
    CarRepository carRepository;
    CarService carService;
    Car c1, c2;

    @BeforeEach
    void setUp(){
        c1 = carRepository.save(new Car("Toyota", "Corolla", 128.5, 100));
        c2 = carRepository.save(new Car("Ford", "Mustand", 500.5, 85));
        carService = new CarService(carRepository);
    }
    @Test
    void testCarsGetId(){
        List<CarResponse> carResponses = carService.getCars(true);
        assertEquals(2, carResponses.size(),"Expects two cars");
        Integer testId = carResponses.get(0).getId();
        assertEquals(1,testId);
    }
    @Test
    void testCarsGetNoId(){
        List<CarResponse> carResponses = carService.getCars(false);
        assertEquals(2, carResponses.size(),"Expects two cars");
        Integer testId = carResponses.get(0).getId();
        assertNull(testId);
    }
    @Test
    void testDeleteCarById(){
        carService.deleteCarById(1);
        assertThrows(ResponseStatusException.class, () -> carService.findCarById(1));
        assertEquals(1, carRepository.count());
    }
    @Test
    void testDeleteCarById_CarIdDoesNotExist(){
        assertThrows(ResponseStatusException.class, () -> carService.findCarById(3));
    }
}
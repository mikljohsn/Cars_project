package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.web.server.ResponseStatusException;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CarServiceH2Test {
    @Autowired
    CarRepository carRepository;
    CarService carService;
    Car c1, c2, c3;

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
        assertEquals(c1.getId(),testId);
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
        carService.deleteCarById(c1.getId());
        assertThrows(ResponseStatusException.class, () -> carService.findCarById(1));
        assertEquals(1, carRepository.count());
    }
    @Test
    void testDeleteCarById_CarIdDoesNotExist(){
        assertThrows(ResponseStatusException.class, () -> carService.deleteCarById(4));
    }
    @Test
    void testFindCarById_carDoesNotExist(){
        assertThrows(ResponseStatusException.class, () -> carService.findCarById(10));
    }
    @Test
    void testEditCar(){
        CarRequest request = new CarRequest(c1);
        request.setBrand("Suzuki");
        carService.editCar(request, request.getId());
        carRepository.flush();
        assertEquals(request.getBrand(),"Suzuki");
    }
    @Test
    void testFindCarsByBrandAndModel(){
        List<CarResponse> carResponses = carService.findCarsByBrandAndModel("Toyota", "Corolla");
        assertThat(carResponses).hasSize(1);
        assertThat(carResponses.get(0).getBrand()).isEqualTo("Toyota");
        assertThat(carResponses.get(0).getModel()).isEqualTo("Corolla");
    }
}
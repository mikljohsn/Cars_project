package dat3.car.repository;

import dat3.car.entity.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class CarRepositoryTest {
    @Autowired
    CarRepository carRepository;
    private boolean isInitialzied = false;

    @BeforeEach
    void setUp() {
        if(!isInitialzied) {
            carRepository.deleteAll();
            carRepository.save(new Car("Toyota", "Corolla", 100, 10));
            carRepository.save(new Car("Honda", "Civic", 110, 15));
            carRepository.save(new Car("Ford", "Focus", 105, 12));
            carRepository.save(new Car("Chevrolet", "Cruze", 120, 18));
            carRepository.save(new Car("Nissan", "Altima", 125, 20));
            carRepository.save(new Car("BMW", "X3", 200, 25));
            isInitialzied = true;
        }
    }
    @Test
    public void testAll(){
        Long count = carRepository.count();
        assertEquals(6,count);
    }
}
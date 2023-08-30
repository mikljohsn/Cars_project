package dat3.car.service;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class CarService {
    CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<CarResponse> getCars(boolean includeAll){
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(((car) -> new CarResponse(car, includeAll))).toList();
    }
    public CarResponse addCar(CarRequest body){
        if(carRepository.existsById(body.getId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Car with this id already exists"); //behøves nok ikk da ID er autogen
        }
        Car newCar = CarRequest.getCarEntity(body);
        newCar = carRepository.save(newCar);
        return new CarResponse(newCar, true);
    }
    public ResponseEntity<Boolean> editCar(CarRequest body, int id){
        Car car = carRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this ID does not exist"));
        if(!(body.getId() == id)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cannot change ID");
        }
        car.setBrand(body.getBrand());
        car.setModel(body.getModel());
        car.setPricePrDay(body.getPricePrDay());
        car.setBestDiscount(body.getBestDiscount());
        carRepository.save(car);
        return ResponseEntity.ok(true);
    }
    public CarResponse findCarById(int id){
        Car car = carRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this ID does not exist"));
        return new CarResponse(car, true);
    }
    public void deleteCarById(int id){
        Car car = carRepository.findById(id).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this ID does not exist"));
        carRepository.delete(car);
    }
}

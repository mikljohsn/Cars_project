package dat3.car.api;

import dat3.car.dto.CarRequest;
import dat3.car.dto.CarResponse;
import dat3.car.entity.Car;
import dat3.car.service.CarService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }
    @GetMapping
    public List<CarResponse> getCars(){
        return carService.getCars(false);
    }
    @GetMapping("/admin")
    public List<CarResponse> getCarsAll(){
        return carService.getCars(true);
    }
    @GetMapping(path = "{id}")
    public CarResponse findCarById(@PathVariable int id){
        return carService.findCarById(id);
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CarResponse addCar(@RequestBody CarRequest body){
        return carService.addCar(body);
    }
    @PutMapping("/{id}")
    ResponseEntity<Boolean> editMember(@RequestBody CarRequest body, @PathVariable int id){
        return carService.editCar(body,id);
    }
    @DeleteMapping("/{id}")
    public void deleteCarById(@PathVariable int id){
        carService.deleteCarById(id);
    }
    @GetMapping("/findByBrandAndModel")
    public List<CarResponse> findCarsByBrandAndModel(@RequestParam String brand, @RequestParam String model) {
        return carService.findCarsByBrandAndModel(brand, model);
    }
}

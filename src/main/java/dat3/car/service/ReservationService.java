package dat3.car.service;

import dat3.car.dto.ReservationRequest;
import dat3.car.dto.ReservationResponse;
import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    CarRepository carRepository;
    MemberRepository memberRepository;

    public ReservationService(ReservationRepository reservationRepository, CarRepository carRepository, MemberRepository memberRepository) {
        this.reservationRepository = reservationRepository;
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
    }
    public ReservationResponse reserveCar(ReservationRequest body){
        if(body.getDate().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Date incorrect");
        }
        Member member = memberRepository.findById(body.getUsername()).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Member with this username does not exist."));
        Car car = carRepository.findById(body.getCarId()).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Car with this ID does not exist."));
        if(reservationRepository.existsByCar_IdAndRentalDate(body.getCarId(),body.getDate())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reservation for this car and date already exists.");
        }
        Reservation reservation = reservationRepository.save(new Reservation(body.getDate(), car, member)); //save så den giver ID i databasen
        return new ReservationResponse(reservation);
    }
}

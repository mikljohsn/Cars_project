package dat3.car.config;

import dat3.car.entity.Car;
import dat3.car.entity.Member;
import dat3.car.entity.Reservation;
import dat3.car.repository.CarRepository;
import dat3.car.repository.MemberRepository;
import dat3.car.repository.ReservationRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DeveloperData implements ApplicationRunner {
    CarRepository carRepository;
    MemberRepository memberRepository;
    ReservationRepository reservationRepository;

    public DeveloperData(CarRepository carRepository, MemberRepository memberRepository, ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        createCars();
        createMembers();

        Member member = new Member("Testbrugernavn", "12354", "email@email", "John",
                "john", "street 1", "city1", "zip1");
        Car car = new Car("Brand1", "Model1", 100, 50);
        memberRepository.save(member);
        carRepository.save(car);
        LocalDate date1 = LocalDate.now().plusDays(2);
        LocalDate date2 = LocalDate.now().plusDays(3);
        Reservation reservation1 = new Reservation(date1,car,member);
        Reservation reservation2 = new Reservation(date2,car,member);
        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);

        System.out.println(car.getReservations().size());
        System.out.println(member.getReservations().size());
    }
    public void createCars(){
        List<Car> carList = new ArrayList<>();

        carList.add(new Car("Toyota", "Corolla", 100, 10));
        carList.add(new Car("Honda", "Civic", 110, 15));
        carList.add(new Car("Ford", "Focus", 105, 12));
        carList.add(new Car("Chevrolet", "Cruze", 120, 18));
        carList.add(new Car("Nissan", "Altima", 125, 20));
        carList.add(new Car("BMW", "X3", 200, 25));
        carList.add(new Car("Mercedes-Benz", "E-Class", 220, 30));
        carList.add(new Car("Audi", "A4", 210, 22));
        carList.add(new Car("Lexus", "RX", 230, 28));
        carList.add(new Car("Hyundai", "Elantra", 95, 8));
        carList.add(new Car("Kia", "Optima", 100, 10));
        carList.add(new Car("Volkswagen", "Golf", 115, 14));
        carList.add(new Car("Subaru", "Outback", 150, 18));
        carList.add(new Car("Mazda", "CX-5", 140, 16));
        carList.add(new Car("Jeep", "Wrangler", 180, 20));
        carList.add(new Car("Porsche", "911", 250, 35));
        carList.add(new Car("Tesla", "Model 3", 300, 40));
        carList.add(new Car("Volvo", "XC90", 220, 28));
        carList.add(new Car("Land Rover", "Discovery", 280, 33));
        carList.add(new Car("Chrysler", "300", 190, 22));
        carList.add(new Car("Ferrari", "488 GTB", 350, 50));
        carList.add(new Car("Jaguar", "F-Type", 270, 30));
        carList.add(new Car("Acura", "NSX", 330, 45));
        carList.add(new Car("Lincoln", "Navigator", 240, 28));
        carList.add(new Car("Buick", "Enclave", 200, 25));
        carList.add(new Car("Cadillac", "XT5", 210, 22));
        carList.add(new Car("Infiniti", "Q50", 230, 28));
        carList.add(new Car("Mini", "Cooper", 150, 18));
        carList.add(new Car("Mitsubishi", "Outlander", 140, 16));
        carList.add(new Car("Nissan", "Rogue", 160, 20));
        carList.add(new Car("Porsche", "Cayenne", 290, 35));
        carList.add(new Car("Toyota", "Camry", 120, 12));
        carList.add(new Car("Volkswagen", "Passat", 125, 14));
        carList.add(new Car("Ram", "1500", 200, 20));
        carList.add(new Car("Maserati", "Ghibli", 280, 35));
        carList.add(new Car("Alfa Romeo", "Giulia", 260, 30));
        carList.add(new Car("Genesis", "G70", 240, 28));
        carList.add(new Car("Kia", "Sorento", 170, 18));
        carList.add(new Car("Hyundai", "Tucson", 150, 16));
        carList.add(new Car("Ford", "Escape", 160, 18));
        carList.add(new Car("Chevrolet", "Equinox", 170, 20));
        carList.add(new Car("Jeep", "Grand Cherokee", 220, 30));
        carList.add(new Car("Honda", "CR-V", 180, 22));
        carList.add(new Car("Lexus", "NX", 210, 26));
        carList.add(new Car("Mercedes-Benz", "GLC", 240, 32));
        carList.add(new Car("Audi", "Q5", 220, 28));
        carList.add(new Car("Subaru", "Forester", 170, 18));
        carList.add(new Car("Tesla", "Model Y", 320, 45));
        carList.add(new Car("Volvo", "XC60", 250, 30));
        carList.add(new Car("Land Rover", "Range Rover", 300, 40));
        carList.add(new Car("Toyota", "Highlander", 200, 22));
        carList.add(new Car("Mazda", "Mazda3", 110, 10));
        carList.add(new Car("Nissan", "Sentra", 120, 12));
        carList.add(new Car("Volkswagen", "Tiguan", 170, 18));

        carRepository.saveAll(carList);
    }
    public void createMembers(){
        List<Member> memberList = new ArrayList<>();

        memberList.add(new Member("jane_smith", "smithpass", "jane.smith@example.com", "Jane", "Smith", "456 Elm St", "Los Angeles", "90001"));
        memberList.add(new Member("michaelJ", "mikepass", "michael.johnson@example.com", "Michael", "Johnson", "789 Oak St", "Chicago", "60601"));
        memberList.add(new Member("emilyW", "emilypass", "emily.williams@example.com", "Emily", "Williams", "101 Maple St", "Houston", "77001"));
        memberList.add(new Member("davidBrown", "brownpass", "david.brown@example.com", "David", "Brown", "222 Pine St", "Miami", "33101"));
        memberList.add(new Member("sarahC", "sarahpass", "sarah.connor@example.com", "Sarah", "Connor", "555 Elm St", "Los Angeles", "90002"));
        memberList.add(new Member("robertP", "robertpass", "robert.parker@example.com", "Robert", "Parker", "777 Oak St", "Chicago", "60602"));

        memberRepository.saveAll(memberList);
    }
}

package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "car")
public class Car extends AdminDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "car_brand", length = 50, nullable = false)
    private String brand;
    @Column(name = "car_model", length = 60, nullable = false)
    private String model;
    @Column(name = "rental_price_day")
    private double pricePrDay;
    @Column(name = "max_discount")
    private int bestDiscount;

    @OneToMany(mappedBy = "car")
    private List <Reservation> reservations; //er ikke nødvendig for at lave selve reservationen
    public Car(String brand, String model, double pricePrDay, int bestDiscount) {
        this.brand = brand;
        this.model = model;
        this.pricePrDay = pricePrDay;
        this.bestDiscount = bestDiscount;
    }
    public void addReservation(Reservation reservation){
        if(reservations == null){
            reservations = new ArrayList<>();
        }
        reservations.add(reservation);
    }

}

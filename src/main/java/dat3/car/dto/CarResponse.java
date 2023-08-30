package dat3.car.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.car.entity.Car;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarResponse {
    private int id;
    private String brand;
    private String model;
    private double pricePrDay;
    private Integer bestDiscount;

    public CarResponse(Car c, boolean includeAll){
        this.brand = c.getBrand();
        this.model = c.getModel();
        this.pricePrDay = c.getPricePrDay();
        if (includeAll){
            this.id = c.getId();
            this.bestDiscount = c.getBestDiscount();
        }
    }
}

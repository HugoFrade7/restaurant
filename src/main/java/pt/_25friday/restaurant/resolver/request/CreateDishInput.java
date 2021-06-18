package pt._25friday.restaurant.resolver.request;

import lombok.Data;
import pt._25friday.restaurant.domain.enums.DishType;

import java.util.UUID;

@Data
public class CreateDishInput {
    private String name;
    private DishType type;
    private Float cost;
    private UUID restaurantId;
}

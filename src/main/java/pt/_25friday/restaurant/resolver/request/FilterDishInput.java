package pt._25friday.restaurant.resolver.request;

import lombok.Data;
import pt._25friday.restaurant.domain.enums.City;
import pt._25friday.restaurant.domain.enums.DishType;

import java.util.UUID;

@Data
public class FilterDishInput {

    private UUID id;

    private String name;

    private Float cost;

    private DishType type;

    private UUID restaurantId;
}

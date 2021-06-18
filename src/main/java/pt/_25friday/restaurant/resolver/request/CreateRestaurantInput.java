package pt._25friday.restaurant.resolver.request;

import lombok.Data;
import pt._25friday.restaurant.domain.enums.City;

@Data
public class CreateRestaurantInput {

    private String name;

    private City location;
}

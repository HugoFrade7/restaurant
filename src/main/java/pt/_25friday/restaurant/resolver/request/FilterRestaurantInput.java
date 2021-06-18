package pt._25friday.restaurant.resolver.request;

import lombok.Data;
import pt._25friday.restaurant.domain.enums.City;

import java.util.UUID;

@Data
public class FilterRestaurantInput {

    private UUID id;

    private String name;

    private City location;
}

package pt._25friday.restaurant.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt._25friday.restaurant.domain.entity.Dish;
import pt._25friday.restaurant.domain.entity.Restaurant;
import pt._25friday.restaurant.resolver.request.CreateDishInput;
import pt._25friday.restaurant.resolver.request.CreateRestaurantInput;
import pt._25friday.restaurant.resolver.request.DeleteEntityInput;
import pt._25friday.restaurant.service.RestaurantService;

@RequiredArgsConstructor
@Component
public class MutationResolver implements GraphQLMutationResolver {

    private final RestaurantService restaurantService;

    public Restaurant createRestaurant(CreateRestaurantInput input) {
        return restaurantService.createRestaurant(input);
    }

    public Restaurant deleteRestaurant(DeleteEntityInput input) {
        return restaurantService.deleteRestaurant(input);
    }

    public Dish createDish(CreateDishInput input) {
        return restaurantService.createDish(input);
    }

    public Dish deleteDish(DeleteEntityInput input) {
        return restaurantService.deleteDish(input);
    }

}

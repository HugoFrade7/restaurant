package pt._25friday.restaurant.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pt._25friday.restaurant.domain.entity.Dish;
import pt._25friday.restaurant.domain.entity.Restaurant;
import pt._25friday.restaurant.resolver.request.FilterDishInput;
import pt._25friday.restaurant.resolver.request.FilterRestaurantInput;
import pt._25friday.restaurant.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QueryResolver implements GraphQLQueryResolver {

    private final RestaurantService restaurantService;


    public List<Restaurant> listRestaurants(FilterRestaurantInput input, Integer limit, Integer offset) {
        return restaurantService.filterRestaurants(input, limit, offset);
    }

    public List<Dish> listDishes(FilterDishInput input, Integer limit, Integer offset) {
        return restaurantService.getDishes(input, limit,offset);
    }

}

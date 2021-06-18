package pt._25friday.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt._25friday.restaurant.domain.entity.Dish;
import pt._25friday.restaurant.domain.entity.Restaurant;
import pt._25friday.restaurant.exceptions.NotFoundException;
import pt._25friday.restaurant.repository.DishRepository;
import pt._25friday.restaurant.repository.RestaurantRepository;
import pt._25friday.restaurant.resolver.request.CreateDishInput;
import pt._25friday.restaurant.resolver.request.CreateRestaurantInput;
import pt._25friday.restaurant.resolver.request.DeleteEntityInput;
import pt._25friday.restaurant.resolver.request.FilterDishInput;
import pt._25friday.restaurant.resolver.request.FilterRestaurantInput;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {

    private final DishRepository dishRepository;

    private final RestaurantRepository restaurantRepository;

    public Restaurant createRestaurant(CreateRestaurantInput input) {
        var restaurant = new Restaurant(input.getName(), input.getLocation());
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> filterRestaurants(FilterRestaurantInput input, Integer limit, Integer offset) {
        var page = PageRequest.of(getValueOrDefault(offset), getValueOrDefault(limit));
        return restaurantRepository.filter(input.getId(),
                input.getName(),
                input.getLocation(),
                page);
    }

    private int getValueOrDefault(Integer offset) {
        return offset != null ? offset : 0;
    }

    public Restaurant deleteRestaurant(DeleteEntityInput input) {
        var restaurant = restaurantRepository.findById(input.getId()).orElseThrow(NotFoundException::new);
        restaurantRepository.delete(restaurant);
        return restaurant;
    }

    public Dish createDish(CreateDishInput input) {
        var restaurant = restaurantRepository.findById(input.getRestaurantId()).orElseThrow(NotFoundException::new);
        var dish = new Dish(input.getName(), input.getType(), BigDecimal.valueOf(input.getCost()), restaurant);
        return dishRepository.save(dish);
    }

    public List<Dish> getDishes(FilterDishInput input, Integer limit, Integer offset) {
        var page = PageRequest.of(getValueOrDefault(offset), getValueOrDefault(limit));
        return dishRepository.filter(input.getId(),
                input.getName(),
                BigDecimal.valueOf(input.getCost()),
                input.getType(),
                input.getRestaurantId(),
                page);
    }

    public Dish deleteDish(DeleteEntityInput input) {
        var dish = dishRepository.findById(input.getId()).orElseThrow(NotFoundException::new);
        dishRepository.delete(dish);
        return dish;
    }
}

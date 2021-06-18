package pt._25friday.restaurant.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt._25friday.restaurant.domain.entity.Dish;
import pt._25friday.restaurant.domain.enums.DishType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface DishRepository  extends CrudRepository<Dish, UUID> {

    @Query("from Dish d where"+
            "(coalesce(:id) is null or d.id = :id) and " +
            "(coalesce(:name) is null or d.name = :name) and " +
            "(coalesce(:cost) is null or d.cost = :cost) and " +
            "(coalesce(:type) is null or d.type = :type) and " +
            "(coalesce(:restaurantId) is null or d.restaurant.id = :restaurantId)")
    List<Dish> filter(UUID id, String name, BigDecimal cost, DishType type, UUID restaurantId, Pageable page);
}

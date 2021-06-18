package pt._25friday.restaurant.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pt._25friday.restaurant.domain.entity.Restaurant;
import pt._25friday.restaurant.domain.enums.City;

import java.util.List;
import java.util.UUID;

public interface RestaurantRepository extends CrudRepository<Restaurant, UUID> {

    @Query("from Restaurant r where " +
            "(coalesce(:id) is null or r.id = :id) and " +
            "(coalesce(:name) is null or r.name = :name) and " +
            "(coalesce(:location) is null or r.location = :location)")
    List<Restaurant> filter (UUID id, String name, City location, Pageable pageable);

}

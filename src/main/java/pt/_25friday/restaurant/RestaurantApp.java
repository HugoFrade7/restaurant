package pt._25friday.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestaurantApp {

    public static void main(String[] args) {
        SpringApplication.run(RestaurantApp.class, args);
    }

}
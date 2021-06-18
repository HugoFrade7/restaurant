package pt._25friday.restaurant.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pt._25friday.restaurant.domain.enums.City;
import pt._25friday.restaurant.domain.enums.PostgreSQLEnumType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
@TypeDef(name = "pgsql_enum", typeClass = PostgreSQLEnumType.class)
public class Restaurant extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private City location;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Dish> dishes = new ArrayList<>();

    public Restaurant(String name, City location) {
        this.name = name;
        this.location = location;
    }

}

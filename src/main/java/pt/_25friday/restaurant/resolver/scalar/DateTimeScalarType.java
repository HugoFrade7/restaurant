package pt._25friday.restaurant.resolver.scalar;

import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class DateTimeScalarType extends GraphQLScalarType {

    public DateTimeScalarType() {
        super("DateTime", "DateTime value", new Coercing<Instant, String>() {
            @Override
            public String serialize(Object o) {
                Instant instant = (Instant) o;
                return instant.toString();
            }

            @Override
            public Instant parseValue(Object o) {
                return getInstant(o);
            }

            @Override
            public Instant parseLiteral(Object o) {
                return getInstant(o);
            }

            private Instant getInstant(Object o) {
                String value = String.valueOf(o);
                try {
                    return Instant.parse(value);
                } catch (Throwable t) {
                    log.error("Error parsing instant", t);
                    return null;
                }
            }

        });
    }

}

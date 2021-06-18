package pt._25friday.restaurant.resolver.request;

import lombok.Data;

import java.util.UUID;

@Data
public class DeleteEntityInput {
    private UUID id;
}

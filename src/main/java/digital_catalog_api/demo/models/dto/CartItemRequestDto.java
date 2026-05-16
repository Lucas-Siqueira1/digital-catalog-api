package digital_catalog_api.demo.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItemRequestDto {

    @NotNull
    private UUID productId;

    @NotNull
    @Min(1)
    private Integer quantity;
}

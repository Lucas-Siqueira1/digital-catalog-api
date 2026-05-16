package digital_catalog_api.demo.models.dto;

import digital_catalog_api.demo.models.entities.enums.StockStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private BigDecimal price;
    private StockStatus stockStatus;
    private UUID categoryId;
}

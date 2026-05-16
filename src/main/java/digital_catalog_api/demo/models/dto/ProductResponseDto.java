package digital_catalog_api.demo.models.dto;

import digital_catalog_api.demo.models.entities.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductResponseDto {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private StockStatus stockStatus;
    private UUID categoryId;
    private List<String> imagesUrls;
}

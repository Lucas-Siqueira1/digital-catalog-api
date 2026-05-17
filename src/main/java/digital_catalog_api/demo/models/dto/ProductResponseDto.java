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

    public ProductResponseDto(UUID id, String name, String description, BigDecimal price, StockStatus stockStatus) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockStatus = stockStatus;
    }

    public ProductResponseDto(StockStatus stockStatus) {
        this.stockStatus = stockStatus;
    }

    public ProductResponseDto(List<String> imagesUrls) {
        this.imagesUrls = imagesUrls;
    }
}

package digital_catalog_api.demo.models.dto;

import digital_catalog_api.demo.models.entities.enums.StockStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StockStatusRequestDto {

    private StockStatus stockStatus;
}
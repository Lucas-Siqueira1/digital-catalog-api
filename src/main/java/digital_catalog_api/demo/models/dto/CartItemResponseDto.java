package digital_catalog_api.demo.models.dto;

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
public class CartItemResponseDto {
    private UUID id;
    private String productName;
    private Integer quantity;
    private BigDecimal subtotal;
}

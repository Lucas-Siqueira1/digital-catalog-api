package digital_catalog_api.demo.models.dto;
import digital_catalog_api.demo.models.entities.CartItem;
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
public class CartResponseDto {
    private UUID id;
    private String sessionId;
    private List<CartItemResponseDto> items;
    private BigDecimal total;
}


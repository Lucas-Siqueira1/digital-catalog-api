package digital_catalog_api.demo.models.dto;
import digital_catalog_api.demo.models.entities.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WhatsAppOrderDto {

    private List<CartItem> items;
}

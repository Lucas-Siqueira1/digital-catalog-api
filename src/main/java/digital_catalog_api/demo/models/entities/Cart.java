package digital_catalog_api.demo.models.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Instant;
import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "carts")
public class Cart implements WhatsAppMessageBuilder{

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> items;
    private String sessionId;
    private Instant createdAt;

    public void addItem(CartItem newItem) {
        items.add(newItem);
    }

    public void removeItem(UUID productId) {
        items.removeIf(item -> item.getId().equals(productId));
    }

    public String buildMessage() {
        BigDecimal total = BigDecimal.ZERO;
        StringBuilder message = new StringBuilder();
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        message.append("Olá! Tenho interesse nos seguintes produtos:\n\n");
        for (CartItem item : items) {
            message.append(item.getQuantity())
                    .append("x ")
                    .append(item.getProduct().getName())
                    .append(" - R$ ")
                    .append(item.getSubtotal())
                    .append("\n\n");
            total = total.add(item.getSubtotal());
        }
        message.append("Valor total: R$ ")
                .append(formato.format(total));

        return message.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

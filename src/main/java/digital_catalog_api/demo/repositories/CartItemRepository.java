package digital_catalog_api.demo.repositories;

import digital_catalog_api.demo.models.entities.CartItem;
import digital_catalog_api.demo.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    void deleteByProduct(Product product);
}

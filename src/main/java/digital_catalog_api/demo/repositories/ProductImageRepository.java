package digital_catalog_api.demo.repositories;

import digital_catalog_api.demo.models.entities.Product;
import digital_catalog_api.demo.models.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductImageRepository extends JpaRepository<ProductImage, UUID> {
    void deleteByProduct(Product product);
}

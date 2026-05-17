package digital_catalog_api.demo.repositories;

import digital_catalog_api.demo.models.entities.Category;
import digital_catalog_api.demo.models.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    public boolean existsByCategory(Category category);
}

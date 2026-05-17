package digital_catalog_api.demo.repositories;

import digital_catalog_api.demo.models.dto.CategoryResponseDto;
import digital_catalog_api.demo.models.entities.Cart;
import digital_catalog_api.demo.models.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    public Optional<Cart> findBySessionId(String sessionId);
}

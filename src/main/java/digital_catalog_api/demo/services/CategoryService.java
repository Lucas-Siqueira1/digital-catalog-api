package digital_catalog_api.demo.services;
import digital_catalog_api.demo.exceptions.ResourceNotFoundException;
import digital_catalog_api.demo.models.dto.CategoryRequestDto;
import digital_catalog_api.demo.models.dto.CategoryResponseDto;
import digital_catalog_api.demo.models.entities.Category;
import digital_catalog_api.demo.repositories.CategoryRepository;
import digital_catalog_api.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;

    public List<CategoryResponseDto> findAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public CategoryResponseDto findCategoryById(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));

        return toDto(category);
    }

    public CategoryResponseDto insert(CategoryRequestDto newCategory) {
        Category category = new Category();
        category.setDescription(newCategory.getDescription());
        category.setName(newCategory.getName());
        Category saved = categoryRepository.save(category);

        return toDto(saved);
    }

    public void delete(UUID id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        if(productRepository.existsByCategory(category)) {
            throw new IllegalStateException("Categoria possui produtos vinculados");
        }
        categoryRepository.deleteById(id);
    }

    private CategoryResponseDto toDto(Category category) {
        CategoryResponseDto dto = new CategoryResponseDto();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setDescription(category.getDescription());

        return dto;
    }
}

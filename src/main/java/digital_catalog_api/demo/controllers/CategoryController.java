package digital_catalog_api.demo.controllers;
import digital_catalog_api.demo.models.dto.CategoryRequestDto;
import digital_catalog_api.demo.models.dto.CategoryResponseDto;
import digital_catalog_api.demo.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDto>> findAllCategories() {
        List<CategoryResponseDto> categories = categoryService.findAllCategories();
        return ResponseEntity.ok().body(categories);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CategoryResponseDto> findCategoryById(@PathVariable UUID id) {
        CategoryResponseDto category = categoryService.findCategoryById(id);
        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDto> insert(@RequestBody CategoryRequestDto newCategory) {
        CategoryResponseDto category = categoryService.insert(newCategory);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(category.getId())
                .toUri();
        return ResponseEntity.created(uri).body(category);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

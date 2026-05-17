package digital_catalog_api.demo.controllers;
import digital_catalog_api.demo.models.dto.ProductRequestDto;
import digital_catalog_api.demo.models.dto.ProductResponseDto;
import digital_catalog_api.demo.models.dto.StockStatusRequestDto;
import digital_catalog_api.demo.models.entities.enums.StockStatus;
import digital_catalog_api.demo.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable UUID id) {
        ProductResponseDto product = productService.findProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> insert(@RequestBody ProductRequestDto newProduct) {
        ProductResponseDto product = productService.insert(newProduct);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(uri).body(product);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> updateGeral(@PathVariable UUID id, @RequestBody ProductRequestDto newProduct) {
        ProductResponseDto product = productService.updateGeral(id, newProduct);
        return ResponseEntity.ok().body(product);
    }

    @PatchMapping(value = "/{id}/stock")
    public ResponseEntity<ProductResponseDto> updateStock(@PathVariable UUID id, @RequestBody StockStatusRequestDto stockStatus) {
        ProductResponseDto product = productService.updateStock(id, stockStatus.getStockStatus());
        return ResponseEntity.ok().body(product);
    }
}

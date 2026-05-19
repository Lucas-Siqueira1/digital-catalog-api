package digital_catalog_api.demo.controllers;
import digital_catalog_api.demo.models.dto.ProductRequestDto;
import digital_catalog_api.demo.models.dto.ProductResponseDto;
import digital_catalog_api.demo.models.dto.StockStatusRequestDto;
import digital_catalog_api.demo.models.entities.enums.StockStatus;
import digital_catalog_api.demo.services.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> findAll() {
        List<ProductResponseDto> products = productService.findAll();
        return ResponseEntity.ok().body(products);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProductResponseDto> findProductById(@PathVariable UUID id) {
        ProductResponseDto product = productService.findProductById(id);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> insert(@ModelAttribute ProductRequestDto newProduct,
                                                     @RequestParam(value = "images", required = false) List<MultipartFile> images ) throws IOException {
        ProductResponseDto product = productService.insert(newProduct, images);
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

    @PostMapping(value = "/{id}/images")
    public ResponseEntity<Void> uploadImage(@PathVariable UUID id,
                                            @RequestParam("file") MultipartFile file) throws IOException {
        productService.uploadImage(id, file);
        return ResponseEntity.noContent().build();
    }
}

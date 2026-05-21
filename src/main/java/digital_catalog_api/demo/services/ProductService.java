package digital_catalog_api.demo.services;
import digital_catalog_api.demo.exceptions.ResourceNotFoundException;
import digital_catalog_api.demo.models.dto.ProductRequestDto;
import digital_catalog_api.demo.models.dto.ProductResponseDto;
import digital_catalog_api.demo.models.entities.Category;
import digital_catalog_api.demo.models.entities.Product;
import digital_catalog_api.demo.models.entities.ProductImage;
import digital_catalog_api.demo.models.entities.enums.StockStatus;
import digital_catalog_api.demo.repositories.CategoryRepository;
import digital_catalog_api.demo.repositories.ProductImageRepository;
import digital_catalog_api.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CloudinaryService cloudinaryService;
    @Autowired
    ProductImageRepository productImageRepository;

    public List<ProductResponseDto> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ProductResponseDto findProductById(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId));

        return toDto(product);
    }

    @Transactional
    public ProductResponseDto insert(ProductRequestDto newProduct, List<MultipartFile> images) throws IOException {
        Product product = new Product();
        Category category = categoryRepository.findById(newProduct.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(newProduct.getCategoryId()));
        product.setCategory(category);
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setDescription(newProduct.getDescription());
        product.setStockStatus(newProduct.getStockStatus());

        Product saved = productRepository.save(product);

        if (images != null && !images.isEmpty()) {
            List<ProductImage> productImages = new ArrayList<>();

            for (MultipartFile file : images) {
                String imageUrl = cloudinaryService.uploadImage(file);
                ProductImage img = new ProductImage();
                img.setImageUrl(imageUrl);
                img.setProduct(saved);
                productImages.add(img);
            }

            saved.setImages(productImages);
            productRepository.save(saved);
        }

        return toDto(saved);
    }

    public ProductResponseDto updateGeral(UUID productId, ProductRequestDto newProduct) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId));
        Category category = categoryRepository.findById(newProduct.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(newProduct.getCategoryId()));
        product.setCategory(category);
        product.setName(newProduct.getName());
        product.setPrice(newProduct.getPrice());
        product.setDescription(newProduct.getDescription());
        product.setStockStatus(newProduct.getStockStatus());

        Product saved = productRepository.save(product);

        return toDto(saved);
    }

    public ProductResponseDto updateStock(UUID productId, StockStatus stockStatus) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId));
        product.setStockStatus(stockStatus);

        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    public void delete(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
        productRepository.delete(product);
    }

    public void uploadImage(UUID productId, MultipartFile file) throws IOException {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException(productId));

        String imageUrl = cloudinaryService.uploadImage(file);

        ProductImage image = new ProductImage();
        image.setImageUrl(imageUrl);
        image.setProduct(product);
        image.setDisplayOrder(product.getImages().size() + 1);

        productImageRepository.save(image);
    }

    private ProductResponseDto toDto(Product product) {
        ProductResponseDto dto = new ProductResponseDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setStockStatus(product.getStockStatus());
        dto.setCategoryId(product.getCategory().getId());

        return dto;
    }
}

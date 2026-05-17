package digital_catalog_api.demo.services;
import digital_catalog_api.demo.exceptions.ResourceNotFoundException;
import digital_catalog_api.demo.models.dto.CartItemRequestDto;
import digital_catalog_api.demo.models.dto.CartItemResponseDto;
import digital_catalog_api.demo.models.dto.CartResponseDto;
import digital_catalog_api.demo.models.entities.Cart;
import digital_catalog_api.demo.models.entities.CartItem;
import digital_catalog_api.demo.models.entities.Product;
import digital_catalog_api.demo.repositories.CartItemRepository;
import digital_catalog_api.demo.repositories.CartRepository;
import digital_catalog_api.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartService {
    
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartItemRepository cartItemRepository;
    @Value("${whatsapp.number}")
    private String whatsappNumber;
    

    public void addItem(String sessionId, CartItemRequestDto dto) {
        Cart cart = cartRepository.findBySessionId(sessionId);
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException(dto.getProductId()));
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());

        cartItemRepository.save(item);
    }

    public void removeItem(UUID cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public Cart createCart(String sessionId) {
        Cart cart = new Cart();
        cart.setSessionId(sessionId);
        cart.setCreatedAt(Instant.now());
        cart.setItems(new ArrayList<>());
        return cartRepository.save(cart);
    }

    public void clearCart(String sessionId) {
        Cart cart = cartRepository.findBySessionId(sessionId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }
    //getCart
    public CartResponseDto getCart(String sessionId) {
        Cart cart = cartRepository.findBySessionId(sessionId);

        return toDto(cart);
    }

    public String buildWhatsappRedirectUrl(String sessionId) {
        Cart cart = cartRepository.findBySessionId(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException(sessionId));
        String message = cart.buildMessage();
        String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8);

        return "https://wa.me/" + whatsappNumber + "text=" + encodedMessage;
    }

    private CartItemResponseDto toDto(CartItem item) {
        CartItemResponseDto dto = new CartItemResponseDto();
        dto.setId(item.getId());
        dto.setProductName(item.getProduct().getName());
        dto.setQuantity(item.getQuantity());
        dto.setSubtotal(item.getSubtotal());
        return dto;
    }

    private CartResponseDto toDto(Cart cart) {
        CartResponseDto dto = new CartResponseDto();
        dto.setId(cart.getId());
        dto.setSessionId(cart.getSessionId());
        dto.setItems(cart.getItems().stream()
                .map(this::toDto)
                .collect(Collectors.toList()));
        dto.setTotal(cart.getItems().stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
        return dto;
    }
}

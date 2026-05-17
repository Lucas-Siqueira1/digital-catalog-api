package digital_catalog_api.demo.controllers;
import digital_catalog_api.demo.models.dto.CartItemRequestDto;
import digital_catalog_api.demo.models.dto.CartResponseDto;
import digital_catalog_api.demo.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(value = "/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = "/{sessionId}/items")
    public ResponseEntity<Void> addItem(@PathVariable String sessionId, @RequestBody CartItemRequestDto dto) {
        cartService.addItem(sessionId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(value = "/{sessionId}/items/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable String sessionId, @PathVariable UUID id) {
        cartService.removeItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{sessionId}")
    public ResponseEntity<CartResponseDto> createCart(@PathVariable String sessionId) {
        CartResponseDto newCart = cartService.createCart(sessionId);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newCart.getId())
                .toUri();
        return ResponseEntity.created(uri).body(newCart);
    }

    @DeleteMapping(value = "/{sessionId}/clear")
    public ResponseEntity<Void> clearCart(@PathVariable String sessionId) {
        cartService.clearCart(sessionId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{sessionId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable String sessionId) {
        CartResponseDto cart = cartService.getCart(sessionId);
        return ResponseEntity.ok().body(cart);
    }

    @GetMapping(value = "/{sessionId}/whatsapp")
    public ResponseEntity<String> buildWhatsappRedirectUrl(@PathVariable String sessionId) {
        String finalUrl = cartService.buildWhatsappRedirectUrl(sessionId);
        return ResponseEntity.ok().body(finalUrl);
    }
}

package practice.cache_strategy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import practice.cache_strategy.controller.request.ProductRequest;
import practice.cache_strategy.domain.Product;
import practice.cache_strategy.service.ProductService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductApiController {

    private final ProductService productService;

    @PostMapping
    public Product save(@RequestBody ProductRequest request) {
        log.info("[Controller] ProductApiController.save() 호출");

        Product product = request.toEntity();
        return productService.save(product);
    }

    @GetMapping
    public List<Product> getProducts() {
        log.info("[Controller] ProductApiController.getProducts() 호출");

        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Long productId) {
        log.info("[Controller] ProductApiController.getProduct({}) 호출", productId);

        return productService.getProduct(productId);
    }
}

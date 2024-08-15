package practice.cache_strategy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.cache_strategy.repository.ProductRepository;
import practice.cache_strategy.service.ProductService;
import practice.cache_strategy.service.ProductServiceV1;

@Configuration
@RequiredArgsConstructor
public class NoCacheConfig {

    private final ProductRepository productRepository;

    @Bean
    public ProductService productService() {
        return new ProductServiceV1(productRepository);
    }
}

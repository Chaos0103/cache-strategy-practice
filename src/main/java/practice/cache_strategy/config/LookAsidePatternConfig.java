package practice.cache_strategy.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import practice.cache_strategy.repository.ProductCacheRepository;
import practice.cache_strategy.repository.ProductRepository;
import practice.cache_strategy.service.ProductService;
import practice.cache_strategy.service.ProductServiceV2;

@Configuration
@RequiredArgsConstructor
public class LookAsidePatternConfig {

    private final ProductRepository productRepository;
    private final ProductCacheRepository productCacheRepository;

    @Bean
    public ProductService productService() {
        return new ProductServiceV2(productRepository, productCacheRepository);
    }
}

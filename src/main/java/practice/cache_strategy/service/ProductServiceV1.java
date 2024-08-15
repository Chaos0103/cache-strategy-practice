package practice.cache_strategy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.cache_strategy.domain.Product;
import practice.cache_strategy.repository.ProductRepository;

import java.util.List;

/**
 * V1: 캐시 미적용 서비스
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceV1 implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(Product product) {
        log.info("[Service] ProductServiceV1.save() 호출");
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        log.info("[Service] ProductServiceV1.getProducts() 호출");
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long productId) {
        log.info("[Service] ProductServiceV1.getProduct({}) 호출", productId);
        return productRepository.findById(productId)
            .orElse(null);
    }
}

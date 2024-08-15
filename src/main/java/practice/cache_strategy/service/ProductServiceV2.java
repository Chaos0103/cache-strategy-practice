package practice.cache_strategy.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.cache_strategy.domain.Product;
import practice.cache_strategy.repository.ProductCacheRepository;
import practice.cache_strategy.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * V2: 캐시 전략(Look Aside 패턴) 적용 서비스
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceV2 implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCacheRepository productCacheRepository;

    @Override
    public Product save(Product product) {
        log.info("[Service] ProductServiceV2.save() 호출");
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        log.info("[Service] ProductServiceV2.getProducts() 호출");
        return productCacheRepository.getValues();
    }

    @Override
    public Product getProduct(Long productId) {
        log.info("[Service] ProductServiceV2.getProduct({}) 호출", productId);
        Optional<Product> product = productCacheRepository.getValue(productId);

        if (product.isEmpty()) {
            return cacheMiss(productId);
        }

        cacheHit(productId);
        return product.get();
    }

    private Product cacheMiss(Long productId) {
        log.info("[Service] ProductServiceV2.cacheMiss({})", productId);
        Product product = productRepository.findById(productId)
            .orElseThrow(NoSuchElementException::new);
        log.info("[Service] 캐시 업데이트");
        productCacheRepository.setValueList(product);
        return product;
    }

    private void cacheHit(Long productId) {
        log.info("[Service] ProductServiceV2.cacheHit({})", productId);
    }
}

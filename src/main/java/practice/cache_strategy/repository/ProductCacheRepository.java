package practice.cache_strategy.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import practice.cache_strategy.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductCacheRepository {

    private static final String PRODUCT_CACHE_KEY = "products";

    private final RedisTemplate<String, Product> redisTemplate;

    public void setValueList(Product value) {
        log.info("[Cache] ProductCacheRepository.setValueList() 호출");
        ListOperations<String, Product> operations = redisTemplate.opsForList();
        operations.rightPush(PRODUCT_CACHE_KEY, value);
    }

    public List<Product> getValues() {
        log.info("[Cache] ProductCacheRepository.getValues() 호출");
        return getProducts();
    }

    public Optional<Product> getValue(Long productId) {
        log.info("[Cache] ProductCacheRepository.getValue({}) 호출", productId);
        List<Product> products = getProducts();
        return products.stream()
            .filter(p -> p.getId().equals(productId))
            .findFirst();
    }

    private List<Product> getProducts() {
        Long size = redisTemplate.opsForList().size(PRODUCT_CACHE_KEY);
        return size == 0 ? new ArrayList<>() : redisTemplate.opsForList().range(PRODUCT_CACHE_KEY, 0, size - 1);
    }
}

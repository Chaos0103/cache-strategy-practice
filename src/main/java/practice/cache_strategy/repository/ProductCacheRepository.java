package practice.cache_strategy.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import practice.cache_strategy.domain.Product;

@Repository
@RequiredArgsConstructor
public class ProductCacheRepository {

    private final RedisTemplate<Long, Product> redisTemplate;

    public void setValue(Long key, Product value) {
        ValueOperations<Long, Product> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }
}

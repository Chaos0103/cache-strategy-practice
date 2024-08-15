package practice.cache_strategy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.cache_strategy.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

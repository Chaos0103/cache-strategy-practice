package practice.cache_strategy.service;

import practice.cache_strategy.domain.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    List<Product> getProducts();

    Product getProduct(Long productId);
}

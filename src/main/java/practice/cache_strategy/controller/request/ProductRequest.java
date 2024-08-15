package practice.cache_strategy.controller.request;

import lombok.Getter;
import practice.cache_strategy.domain.Product;

@Getter
public class ProductRequest {

    private String productName;
    private int price;
    private int quantity;

    public Product toEntity() {
        return Product.of(productName, price, quantity);
    }
}

# Cache Strategy Pattern

## 캐시 읽기 전략

### Look Aside 패턴

> 데이터 조회시 캐시에 저장된 데이터가 있는지 우선적으로 확인 후,
> 만약 캐시에 데이터가 없다면 데이터베이스에서 조회하는 전략

* 애플리케이션에서 캐싱을 이용할때 일반적으로 사용되는 캐시 전략
* **반복적인 조회가 많은 호출에 적합**
* 캐시와 데이터베이스가 분리되어 가용되기 때문에 **원하는 데이터만 별도로 구성하여 캐시에 저장**
  * ex) 조회가 많을 것으로 예상되는 상품 도메인에만 캐시 적용 가능
* 캐시와 데이터베이스가 분리되어 가용되기 때문에 **캐시 장애 대비 구성**이 되어있음
  * 캐시(Redis)가 다운되더라도 데이터베이스에서 데이터를 가져올 수 있으므로 서비스 자체는 문제가 없음
* 반면 캐시에 붙어있던 connection이 많았다면, Redis가 다운된 경우 순간적으로 데이터베이스로 요청이 몰려 부하가 발생
* 캐시와 데이터베이스간 정합성 유지 문제가 발생할 수 있으며, 초기 조회시 무조건 데이터베이스를 호출해야 하므로 **단건 호출 빈도가 높은 서비스에 적합하지 않다.**

[ProductServiceV2.java](src/main/java/practice/cache_strategy/service/ProductServiceV2.java)

```java

@Override
public Product getProduct(Long productId) {
    log.info("[Service] ProductServiceV2.getProduct({}) 호출", productId);
    //1. 캐시에 데이터가 있는지 확인
    Optional<Product> product = productCacheRepository.getValue(productId);

    //2. 캐시에 데이터가 없을 경우 데이터베이스에서 데이터 조회(Cache Miss)
    if (product.isEmpty()) {
        return cacheMiss(productId);
    }

    //Cache Hit
    cacheHit(productId);
    return product.get();
}

private Product cacheMiss(Long productId) {
    log.info("[Service] ProductServiceV2.cacheMiss({})", productId);
    Product product = productRepository.findById(productId)
        .orElseThrow(NoSuchElementException::new);
    //3. 데이터베이스에서 조회한 데이터를 캐시에 저장(업데이트)
    log.info("[Service] 캐시 업데이트");
    productCacheRepository.setValueList(product);
    return product;
}

private void cacheHit(Long productId) {
    log.info("[Service] ProductServiceV2.cacheHit({})", productId);
}
```


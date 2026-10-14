package com.enric.microservices.catalog_service.product;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public Product create(String name, BigDecimal price, boolean available) {
        Product product = new Product(name, price, available);
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
    return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }
}
package com.spring.boot.product.service;

import com.spring.boot.product.dto.ProductRequest;
import com.spring.boot.product.dto.ProductResponse;
import com.spring.boot.product.model.Product;
import com.spring.boot.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void  createPoduct(ProductRequest productRequest) {
        Product product = Product.builder()
                            .name(productRequest.getName())
                            .description(productRequest.getDescription())
                            .price(productRequest.getPrice())
                            .build();
        productRepository.save(product);
        log.info("Product {} is saved successfully. ",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}

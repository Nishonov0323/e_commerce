package com.workshop.zarif.service;

import com.workshop.zarif.dto.ProductCreateRequest;
import com.workshop.zarif.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductCreateRequest request);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getAllProducts();
    ProductResponse updateProduct(Long id, ProductCreateRequest request);
    void deleteProduct(Long id);
}

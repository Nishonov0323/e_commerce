package com.workshop.zarif.service;

import com.workshop.zarif.dto.ProductCreateRequest;
import com.workshop.zarif.dto.ProductResponse;
import com.workshop.zarif.dto.PageResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductCreateRequest request);
    ProductResponse getProductById(Long id);
    PageResponse<ProductResponse> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
    ProductResponse updateProduct(Long id, ProductCreateRequest request);
    void deleteProduct(Long id);
}

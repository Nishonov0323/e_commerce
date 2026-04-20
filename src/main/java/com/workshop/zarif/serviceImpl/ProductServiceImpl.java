package com.workshop.zarif.serviceImpl;

import com.workshop.zarif.dto.ProductCreateRequest;
import com.workshop.zarif.dto.ProductResponse;
import com.workshop.zarif.dto.PageResponse;
import com.workshop.zarif.entity.Category;
import com.workshop.zarif.entity.Product;
import com.workshop.zarif.entity.ProductStatus;
import com.workshop.zarif.exception.ResourceNotFoundException;
import com.workshop.zarif.repository.CategoryRepository;
import com.workshop.zarif.repository.ProductRepository;
import com.workshop.zarif.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok orqali dependency injection uchun
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductCreateRequest request) {
        // 1. Kategoriyani tekshirish
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Kategoriya topilmadi!"));

        // 2. Request (DTO) ni Entity-ga o'girish
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setVendorId(request.getVendorId());
        product.setDescription(request.getDescription());
        if (request.getImageUrls() != null) {
            product.setImageUrls(request.getImageUrls());
        }
        product.setCategory(category);
        product.setStatus(ProductStatus.ACTIVE);

        // 3. Bazaga saqlash
        Product savedProduct = productRepository.save(product);

        // 4. Entity-ni Response (DTO) ga o'girish va qaytarish
        return mapToResponse(savedProduct);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mahsulot topilmadi!"));
        return mapToResponse(product);
    }

    @Override
    public PageResponse<ProductResponse> getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Product> products = productRepository.findAll(pageable);

        List<ProductResponse> content = products.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();

        return PageResponse.<ProductResponse>builder()
                .content(content)
                .pageNo(products.getNumber())
                .pageSize(products.getSize())
                .totalElements(products.getTotalElements())
                .totalPages(products.getTotalPages())
                .last(products.isLast())
                .build();
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(Long id, ProductCreateRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mahsulot topilmadi!"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Kategoriya topilmadi!"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setVendorId(request.getVendorId());
        product.setDescription(request.getDescription());
        if (request.getImageUrls() != null) {
            product.setImageUrls(request.getImageUrls());
        }
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);
        return mapToResponse(updatedProduct);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Mahsulot topilmadi!"));
        productRepository.delete(product);
    }

    // Yordamchi metod (Mapping)
    private ProductResponse mapToResponse(Product product) {
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setName(product.getName());
        response.setDescription(product.getDescription());
        response.setPrice(product.getPrice());
        response.setStockQuantity(product.getStockQuantity());
        response.setCategoryName(product.getCategory().getName());
        return response;
    }

    // Boshqa metodlar...
}

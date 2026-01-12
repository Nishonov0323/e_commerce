package com.workshop.zarif.repository;

import com.workshop.zarif.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Kategoriya bo'yicha mahsulotlarni topish
    List<Product> findByCategoryId(Long categoryId);

    // Sotuvchi bo'yicha mahsulotlarni topish
    List<Product> findByVendorId(Long vendorId);

    // Qidiruv uchun (Medium daraja uchun muhim)
    List<Product> findByNameContainingIgnoreCase(String name);
}

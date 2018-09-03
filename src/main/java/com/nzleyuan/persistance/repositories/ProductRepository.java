package com.nzleyuan.persistance.repositories;

import com.nzleyuan.persistance.com.nzleyuan.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

package com.kardex.infrastructure.out.repository;


import com.kardex.infrastructure.out.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
}

package com.kardex.infrastructure.out.repository;


import com.kardex.infrastructure.out.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IProductRepository extends JpaRepository<ProductEntity, Long> {
    Page<ProductEntity> findAllByUserId(String userId, Pageable pageable);
    Page<ProductEntity> findByUserIdAndProviderId(String userId, Long providerId, Pageable pageable);

    @Modifying
    @Query("UPDATE ProductEntity p SET p.quantity = :quantity WHERE p.id = :id")
    int updateQuantity(@Param("id")Long productId,@Param("quantity") Integer newQuantity);
}

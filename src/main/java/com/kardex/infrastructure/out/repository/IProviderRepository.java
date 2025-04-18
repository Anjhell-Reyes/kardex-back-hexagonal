package com.kardex.infrastructure.out.repository;

import com.kardex.infrastructure.out.entity.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProviderRepository extends JpaRepository<ProviderEntity, Long> {
}

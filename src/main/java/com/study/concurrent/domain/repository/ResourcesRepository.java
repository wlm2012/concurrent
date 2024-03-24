package com.study.concurrent.domain.repository;

import com.study.concurrent.domain.entity.ResourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourcesRepository extends JpaRepository<ResourcesEntity, Long> {
}

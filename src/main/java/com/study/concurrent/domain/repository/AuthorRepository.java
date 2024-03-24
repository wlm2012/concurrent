package com.study.concurrent.domain.repository;

import com.study.concurrent.domain.entity.AuthorEntity;
import jakarta.annotation.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

@Resource
public interface AuthorRepository extends JpaRepository<AuthorEntity,Long> {
}

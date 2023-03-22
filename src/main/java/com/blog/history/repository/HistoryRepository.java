package com.blog.history.repository;

import com.blog.history.domain.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long>, HistoryCustomRepository {
}

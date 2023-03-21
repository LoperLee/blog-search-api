package com.blog.search.history.repository;

import com.blog.search.history.domain.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<HistoryEntity, Long>, HistoryCustomRepository {
}

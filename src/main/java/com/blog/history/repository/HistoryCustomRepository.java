package com.blog.history.repository;

import com.blog.history.domain.HistoryEntity;

import java.util.List;
import java.util.Optional;

public interface HistoryCustomRepository {
    Optional<HistoryEntity> findByKeyword(String keyword);

    List<HistoryEntity> findTopHistory();
}

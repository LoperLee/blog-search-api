package com.blog.search.history.service;

import com.blog.search.history.domain.History;
import com.blog.search.history.domain.HistoryEntity;
import com.blog.search.history.repository.HistoryRepository;
import com.blog.search.search.domain.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    @EventListener
    @Transactional
    public void publishHistory(SearchRequest request) {
        HistoryEntity entity = historyRepository.findByKeyword(request.getQuery())
                .orElseGet(() ->
                        HistoryEntity.builder()
                                .keyword(request.getQuery())
                                .count(0L)
                                .build()
                );
        entity.increaseCount(1);
        historyRepository.save(entity);
    }

    public List<History> getHistorys() {
        return historyRepository.findTopHistory().stream()
                .map(HistoryEntity::transObject)
                .collect(Collectors.toList());
    }
}

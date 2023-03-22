package com.blog.history;

import com.blog.history.domain.History;
import com.blog.history.domain.HistoryEntity;
import com.blog.history.repository.HistoryRepository;
import com.blog.search.domain.SearchRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;

    @EventListener
    @Async
    @Transactional
    public void publishHistory(SearchRequest request) {
        HistoryEntity history = historyRepository.findByKeyword(request.getQuery())
                .orElseGet(() ->
                        HistoryEntity.builder()
                                .keyword(request.getQuery())
                                .count(0L)
                                .build()
                );
        history.increaseCount(1);
        historyRepository.save(history);
    }

    @Transactional(readOnly = true)
    public List<History> getHistorys() {
        return historyRepository.findTopHistory().stream()
                .map(HistoryEntity::transObject)
                .collect(Collectors.toList());
    }
}

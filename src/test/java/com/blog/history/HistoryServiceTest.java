package com.blog.history;

import com.blog.history.domain.History;
import com.blog.history.domain.HistoryEntity;
import com.blog.history.repository.HistoryRepository;
import com.blog.search.domain.SearchRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HistoryServiceTest {
    private HistoryService historyService;

    @Mock
    private HistoryRepository historyRepository;

    private HistoryEntity historyEntity;

    @BeforeEach
    void beforeSet() {
        historyService = new HistoryService(historyRepository);
        historyEntity = new HistoryEntity(0L, "example", 0L, 0L);
    }

    @Test
    void testPublishHistory() {
        when(historyRepository.findByKeyword("example"))
                .thenReturn(Optional.of(historyEntity));
        when(historyRepository.save(historyEntity))
                .thenReturn(historyEntity);

        historyService.publishHistory(SearchRequest.builder().query("example").build());

        assertEquals(historyEntity.getCount(), 1L);
    }

    @Test
    void testGetHistorys() {
        when(historyRepository.findTopHistory())
                .thenReturn(List.of(historyEntity));

        List<History> result = historyService.getHistorys();

        assertEquals(Stream.of(historyEntity).map(HistoryEntity::transObject).collect(Collectors.toList()), result);
    }
}


package com.blog.history;

import com.blog.history.domain.HistoryEntity;
import com.blog.history.repository.HistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class HistoryControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private HistoryRepository historyRepository;

    @Test
    void testSearchBlogs() throws Exception {
        // given
        historyRepository.save(HistoryEntity.builder().keyword("ex1").count(100L).build());
        historyRepository.save(HistoryEntity.builder().keyword("ex2").count(50L).build());
        historyRepository.save(HistoryEntity.builder().keyword("ex3").count(30L).build());
        historyRepository.save(HistoryEntity.builder().keyword("ex4").count(20L).build());
        historyRepository.save(HistoryEntity.builder().keyword("ex5").count(10L).build());
        historyRepository.save(HistoryEntity.builder().keyword("ex6").count(1L).build());
        historyRepository.save(HistoryEntity.builder().keyword("ex7").count(1L).build());
        historyRepository.save(HistoryEntity.builder().keyword("ex8").count(1L).build());
        historyRepository.save(HistoryEntity.builder().keyword("ex9").count(1L).build());
        historyRepository.save(HistoryEntity.builder().keyword("ex10").count(1L).build());

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/history/rank");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].keyword").value("ex1"));
    }
}

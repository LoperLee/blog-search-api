package com.blog.search.history.repository.impl;

import com.blog.search.history.domain.HistoryEntity;
import com.blog.search.history.repository.HistoryCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.blog.search.history.domain.QHistoryEntity.historyEntity;

@Repository
@RequiredArgsConstructor
public class HistoryCustomRepositoryImpl implements HistoryCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<HistoryEntity> findByKeyword(String keyword) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(historyEntity)
                        .where(historyEntity.keyword.eq(keyword))
                        .fetchOne()
        );
    }

    @Override
    public List<HistoryEntity> findTopHistory() {
        return jpaQueryFactory.selectFrom(historyEntity)
                .orderBy(historyEntity.count.desc())
                .limit(10)
                .fetch();
    }
}

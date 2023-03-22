package com.blog.history.repository.impl;

import com.blog.history.domain.HistoryEntity;
import com.blog.history.domain.QHistoryEntity;
import com.blog.history.repository.HistoryCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HistoryCustomRepositoryImpl implements HistoryCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Optional<HistoryEntity> findByKeyword(String keyword) {
        return Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(QHistoryEntity.historyEntity)
                        .where(QHistoryEntity.historyEntity.keyword.eq(keyword))
                        .fetchOne()
        );
    }

    @Override
    public List<HistoryEntity> findTopHistory() {
        return jpaQueryFactory.selectFrom(QHistoryEntity.historyEntity)
                .orderBy(QHistoryEntity.historyEntity.count.desc())
                .limit(10)
                .fetch();
    }
}

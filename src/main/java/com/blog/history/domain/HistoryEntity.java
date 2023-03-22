package com.blog.history.domain;

import com.blog.core.Translatable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class HistoryEntity implements Translatable<History> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String keyword;

    private Long count;

    @Version
    private Long version;

    public void increaseCount(int increaseNum) {
        this.count += increaseNum;
    }

    @Override
    public History transObject() {
        return History.builder()
                .keyword(keyword)
                .count(count)
                .build();
    }
}

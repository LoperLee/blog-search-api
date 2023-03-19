package com.blog.search.search.provider.kakao.domin;

import com.blog.search.core.Translatable;
import com.blog.search.search.domain.Meta;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoBlogMeta implements Translatable<Meta> {
    private Integer total_count;
    private Integer pageable_count;
    private Boolean is_end;

    @Override
    public Meta transObject() {
        return Meta.builder()
                .totalCount(total_count)
                .pageableCount(pageable_count)
                .isEnd(is_end)
                .build();
    }
}

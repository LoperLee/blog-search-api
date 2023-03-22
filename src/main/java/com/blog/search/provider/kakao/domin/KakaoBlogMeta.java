package com.blog.search.provider.kakao.domin;

import com.blog.core.Translatable;
import com.blog.search.domain.Meta;
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

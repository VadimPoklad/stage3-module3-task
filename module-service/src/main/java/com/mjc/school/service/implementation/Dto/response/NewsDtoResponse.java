package com.mjc.school.service.implementation.Dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record NewsDtoResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createDate,
        LocalDateTime lastUpdateDate,
        AuthorDtoResponse author,
        List<TagDtoResponse> tags
) implements Response {
    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", author=" + author +
                ", tags=" + tags +
                '}';
    }
}
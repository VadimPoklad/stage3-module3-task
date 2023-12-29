package com.mjc.school.service.implementation.Dto.response;

import java.time.LocalDateTime;

public record AuthorDtoResponse(
        Long id,
        String name,
        LocalDateTime createDate,
        LocalDateTime lastUpdateDate
) implements Response {
    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                '}';
    }
}

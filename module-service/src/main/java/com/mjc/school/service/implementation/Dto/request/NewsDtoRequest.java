package com.mjc.school.service.implementation.Dto.request;

import com.mjc.school.service.annatation.Size;
import com.sun.istack.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record NewsDtoRequest(
        Long id,
        @NotNull
        @Size(min = 5, max = 30)
        String title,
        @NotNull
        @Size(min = 5, max = 255)
        String content,
        @NotNull
        LocalDateTime date,
        @NotNull
        Long authorId,
        @NotNull
        String tagIds
) implements Request { }

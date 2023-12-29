package com.mjc.school.service.implementation.Dto.request;

import com.mjc.school.service.annatation.Size;
import com.sun.istack.NotNull;
import lombok.Builder;

@Builder
public record AuthorDtoRequest (
        String id,
        @NotNull
        @Size(min = 3, max = 15)
        String name
)implements Request{}

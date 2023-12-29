package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.Author;
import com.mjc.school.service.implementation.Dto.request.AuthorDtoRequest;
import com.mjc.school.service.implementation.Dto.response.AuthorDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
    List<AuthorDtoResponse> modelListToDtoList(List<Author> modelList);

    AuthorDtoResponse modelToDto(Author model);

    @Mappings({
            @Mapping(target = "lastUpdateDate", ignore = true),
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "news", ignore = true),
    })
    Author dtoToModel(AuthorDtoRequest dto);
}

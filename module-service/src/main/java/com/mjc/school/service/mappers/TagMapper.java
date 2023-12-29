package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.implementation.Dto.request.TagDtoRequest;
import com.mjc.school.service.implementation.Dto.response.TagDtoResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper{
    List<TagDtoResponse> modelListToDtoList(List<Tag> modelList);
    TagDtoResponse modelToDto(Tag model);
    Tag dtoToModel(TagDtoRequest dto);
}
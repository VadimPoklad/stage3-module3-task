package com.mjc.school.service.mappers;

import com.mjc.school.repository.model.News;
import com.mjc.school.service.implementation.Dto.request.NewsDtoRequest;
import com.mjc.school.service.implementation.Dto.response.NewsDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class NewsMapper {
    @Autowired
    protected AuthorMapper authorMapper;
    @Autowired
    protected TagMapper tagMapper;

    public abstract List<NewsDtoResponse> modelListToDtoList(List<News> modelList);

    @Mappings({
            @Mapping(target = "author",
                    expression = "java(authorMapper.modelToDto(model.getAuthor()))"),
            @Mapping(target = "tags",
                    expression = "java(tagMapper.modelListToDtoList(model.getTags()))"),
            @Mapping(target = "s", ignore = true),
    })
    public abstract NewsDtoResponse modelToDto(News model);

    @Mappings({
            @Mapping(target = "lastUpdateDate", ignore = true),
            @Mapping(target = "createDate", ignore = true),
            @Mapping(target = "author", ignore = true),
            @Mapping(target = "tags", ignore = true),
    })
    public abstract News dtoToModel(NewsDtoRequest dto);
}

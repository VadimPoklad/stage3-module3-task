package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.implementation.Dto.request.TagDtoRequest;
import com.mjc.school.service.implementation.Dto.response.TagDtoResponse;
import com.mjc.school.service.mappers.TagMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TagService implements BaseService<TagDtoResponse, TagDtoRequest, Long> {
    private final BaseRepository<Tag, Long> repository;
    private final TagMapper mapper;
    private final Validator validator;

    @Autowired
    public TagService(BaseRepository<Tag, Long> repository, TagMapper mapper, Validator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public TagDtoResponse create(TagDtoRequest requestDto) {
        validator.validateInput(requestDto);
        Tag createdModel = repository.create(mapper.dtoToModel(requestDto));
        return mapper.modelToDto(createdModel);
    }

    @Override
    public List<TagDtoResponse> getAll() {
        List<Tag> list = repository.readAll();
        return mapper.modelListToDtoList(list);

    }

    @Override
    public TagDtoResponse getById(Long id) {
        Optional<Tag> tag = repository.readById(id);
        if(tag.isEmpty()) throw new IllegalArgumentException();
        return mapper.modelToDto(tag.get());

    }

    @Override
    public TagDtoResponse updateById(TagDtoRequest requestDto) {
        Optional<Tag> updatingTag = repository.readById(requestDto.id());
        if (updatingTag.isEmpty())
            throw new IllegalArgumentException("Can not update, because id does not exist");
        Tag updatedTag = repository.update(mapper.dtoToModel(requestDto));
        return mapper.modelToDto(updatedTag);
    }

    @Override
    public boolean removeById(Long id) {
        repository.deleteById(id);
        return true;

    }
}

package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.implementation.Dto.request.AuthorDtoRequest;
import com.mjc.school.service.implementation.Dto.response.AuthorDtoResponse;
import com.mjc.school.service.mappers.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AuthorService implements BaseService<AuthorDtoResponse, AuthorDtoRequest, Long> {
    private final BaseRepository<Author, Long> repository;
    private final AuthorMapper mapper;
    private final Validator validator;

    @Autowired
    public AuthorService(BaseRepository<Author, Long> repository, AuthorMapper mapper, Validator validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    public AuthorDtoResponse create(AuthorDtoRequest requestDto){
        validator.validateInput(requestDto);
        Author author = new Author(requestDto.name());
        Author createdModel = repository.create(author);
        return mapper.modelToDto(createdModel);

    }

    @Override
    public List<AuthorDtoResponse> getAll(){
        return mapper.modelListToDtoList(repository.readAll());

    }

    @Override
    public AuthorDtoResponse getById(Long id){
        Optional<Author> model = repository.readById(id);
        if (model.isEmpty()) {
            throw new IllegalArgumentException("Can not get, because id does not exist");
        }
        return mapper.modelToDto(model.get());

    }

    @Override
    public AuthorDtoResponse updateById(AuthorDtoRequest requestDto){
        validator.validateInput(requestDto);
        Optional<Author> updatingAuthor = repository.readById(
                Long.valueOf(requestDto.id()));
        if (updatingAuthor.isEmpty())
            throw new IllegalArgumentException("Can not update, because id does not exist");
        Author author = mapper.dtoToModel(requestDto);
        return mapper.modelToDto(repository.update(author));

    }

    @Override
    public boolean removeById(Long id){
        repository.deleteById(id);
        return true;
    }

}

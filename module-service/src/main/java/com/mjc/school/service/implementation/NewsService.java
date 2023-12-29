package com.mjc.school.service.implementation;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.implementation.TagRepository;
import com.mjc.school.repository.model.Author;
import com.mjc.school.repository.model.News;
import com.mjc.school.repository.model.Tag;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.implementation.Dto.request.NewsDtoRequest;
import com.mjc.school.service.implementation.Dto.response.NewsDtoResponse;
import com.mjc.school.service.mappers.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NewsService implements BaseService<NewsDtoResponse, NewsDtoRequest, Long> {
    private final BaseRepository<News, Long> newsRepository;
    private final BaseRepository<Author, Long> authorRepository;
    private final BaseRepository<Tag, Long> tagRepository;
    private final NewsMapper mapper;
    private final Validator validator;

    @Autowired
    public NewsService(BaseRepository<News, Long> newsRepository,
                       BaseRepository<Author, Long> authorRepository,
                       BaseRepository<Tag, Long> tagRepository,
                       NewsMapper newsMapper,
                       Validator validator) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
        this.mapper = newsMapper;
        this.validator = validator;
    }


    @Override
    public NewsDtoResponse create(NewsDtoRequest requestDto) {
        validator.validateInput(requestDto);

        Optional<Author> author = authorRepository.readById(requestDto.authorId());
        if (author.isEmpty()) throw new IllegalArgumentException("Author does not exist");
        List<Long> listTags = Arrays.stream(requestDto
                        .tagIds()
                        .split(" "))
                .map(Long::valueOf)
                .toList();
        List<Tag> tags = ((TagRepository) tagRepository).readAllByTagIds(listTags);

        News news = News.builder()
                .title(requestDto.title())
                .content(requestDto.content())
                .createDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .author(author.get())
                .tags(tags).build();

        return mapper.modelToDto(newsRepository.create(news));
    }

    @Override
    public List<NewsDtoResponse> getAll() {
        return mapper.modelListToDtoList(newsRepository.readAll());
    }

    @Override
    public NewsDtoResponse getById(Long id) {
        Optional<News> news = newsRepository.readById(id);
        if (news.isEmpty()) {
            throw new IllegalArgumentException("Can not get, because id does not exist");
        }
        return mapper.modelToDto(news.get());
    }

    public List<NewsDtoResponse> getByTitle(String title) {
        return mapper.modelListToDtoList(
                ((NewsRepository) newsRepository).readByTitle(title));
    }

    public List<NewsDtoResponse> getByContent(String content) {
        return mapper.modelListToDtoList(
                ((NewsRepository) newsRepository).readByContent(content));
    }

    public List<NewsDtoResponse> getByAuthorName(String name) {
        return mapper.modelListToDtoList(
                ((NewsRepository) newsRepository).readByAuthorName(name));
    }

    public List<NewsDtoResponse> getByTagIds(String tagIds) {
        String[] idArray = tagIds.split(" ");
        return mapper.modelListToDtoList(
                ((NewsRepository) newsRepository).readByTagIds(
                        Arrays.stream(idArray).map(Long::valueOf).toList()));

    }

    public List<NewsDtoResponse> getByTagNames(String tagNames) {
        String[] namesArray = tagNames.split(" ");
        return mapper.modelListToDtoList(
                ((NewsRepository) newsRepository).readByTagNames(List.of(namesArray)));
    }

    @Override
    public NewsDtoResponse updateById(NewsDtoRequest requestDto) {

        validator.validateInput(requestDto);
        Optional<News> updatingNews = newsRepository.readById(requestDto.id());
        if (updatingNews.isEmpty())
            throw new IllegalArgumentException("Can not update, because id does not exist");

        Optional<Author> author = authorRepository.readById(requestDto.authorId());
        if (author.isEmpty())
            throw new IllegalArgumentException("Can not update, because author does not exist");
        List<Long> listTags = Arrays.stream(requestDto
                        .tagIds()
                        .split(" "))
                .map(Long::valueOf)
                .toList();
        List<Tag> tags = ((TagRepository) tagRepository).readAllByTagIds(listTags);

        News news = News.builder()
                .id(updatingNews.get().getId())
                .title(requestDto.title())
                .content(requestDto.content())
                .createDate(updatingNews.get().getCreateDate())
                .lastUpdateDate(LocalDateTime.now())
                .author(author.get())
                .tags(tags).build();

        return mapper.modelToDto(newsRepository.update(news));

    }

    @Override
    public boolean removeById(Long id) {
        newsRepository.deleteById(id);
        return true;
    }
}

package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;
import com.mjc.school.service.implementation.Dto.request.NewsDtoRequest;
import com.mjc.school.service.implementation.Dto.response.NewsDtoResponse;
import com.mjc.school.service.implementation.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final NewsService newsService;

    @Autowired
    public NewsController(final NewsService newsService) {
        this.newsService = newsService;
    }

    @Override
    @CommandHandler(operation = 1)
    public List<NewsDtoResponse> readAll() {
        return newsService.getAll();
    }

    @Override
    @CommandHandler(operation = 2)
    public NewsDtoResponse readById(@CommandParam(name = "id") Long id) {
        return newsService.getById(id);
    }

    @CommandHandler(operation = 3)
    public List<NewsDtoResponse> readByAuthorName(@CommandParam(name = "name") String name) {
        return newsService.getByAuthorName(name);
    }

    @CommandHandler(operation = 4)
    public List<NewsDtoResponse> readByTitle(@CommandParam(name = "title") String title) {
        return newsService.getByTitle(title);
    }

    @CommandHandler(operation = 5)
    public List<NewsDtoResponse> readByContent(@CommandParam(name = "content") String content) {
        return newsService.getByContent(content);
    }

    @CommandHandler(operation = 6)
    public List<NewsDtoResponse> readByTagIds(@CommandParam(name = "tagIds") String tagIds) {
        return newsService.getByTagIds(tagIds);
    }

    @CommandHandler(operation = 7)
    public List<NewsDtoResponse> readByTagNames(@CommandParam(name = "tagNames") String tagNames) {
        return newsService.getByTagNames(tagNames);
    }

    @Override
    @CommandHandler(operation = 8)
    public NewsDtoResponse create(@CommandBody NewsDtoRequest dtoRequest) {
        return newsService.create(dtoRequest);
    }

    @Override
    @CommandHandler(operation = 9)
    public NewsDtoResponse update(@CommandBody NewsDtoRequest dtoRequest) {
        return newsService.updateById(dtoRequest);
    }

    @Override
    @CommandHandler(operation = 10)
    public boolean deleteById(@CommandParam(name = "id") Long id) {
        return newsService.removeById(id);
    }
}

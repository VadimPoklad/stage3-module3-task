package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;

import com.mjc.school.service.implementation.Dto.request.TagDtoRequest;
import com.mjc.school.service.implementation.Dto.response.TagDtoResponse;
import com.mjc.school.service.implementation.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class TagController implements BaseController<TagDtoRequest, TagDtoResponse, Long> {

    private final TagService tagService;

    @Autowired
    public TagController(
            final TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    @CommandHandler(operation = 16)
    public List<TagDtoResponse> readAll() {
        return tagService.getAll();
    }

    @Override
    @CommandHandler(operation = 17)
    public TagDtoResponse readById(@CommandParam(name = "id") Long id) {
        return tagService.getById(id);
    }

    @Override
    @CommandHandler(operation = 18)
    public TagDtoResponse create(@CommandBody TagDtoRequest dtoRequest) {
        return tagService.create(dtoRequest);
    }

    @Override
    @CommandHandler(operation = 19)
    public TagDtoResponse update(@CommandBody TagDtoRequest dtoRequest) {
        return tagService.updateById(dtoRequest);
    }

    @Override
    @CommandHandler(operation = 20)
    public boolean deleteById(@CommandParam(name = "id") Long id) {
        return tagService.removeById(id);
    }
}

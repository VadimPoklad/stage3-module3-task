package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandBody;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.annotation.CommandParam;

import com.mjc.school.service.implementation.AuthorService;
import com.mjc.school.service.implementation.Dto.request.AuthorDtoRequest;

import com.mjc.school.service.implementation.Dto.response.AuthorDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class AuthorController implements BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(
            final AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @CommandHandler(operation = 11)
    public List<AuthorDtoResponse> readAll() {
        return authorService.getAll();
    }

    @Override
    @CommandHandler(operation = 12)
    public AuthorDtoResponse readById(@CommandParam(name = "id") Long id) {
        return authorService.getById(id);
    }

    @Override
    @CommandHandler(operation = 13)
    public AuthorDtoResponse create(@CommandBody AuthorDtoRequest dtoRequest) {
        return authorService.create(dtoRequest);
    }

    @Override
    @CommandHandler(operation = 14)
    public AuthorDtoResponse update(@CommandBody AuthorDtoRequest dtoRequest) {
        return authorService.updateById(dtoRequest);
    }

    @Override
    @CommandHandler(operation = 15)
    public boolean deleteById(@CommandParam(name = "id") Long id) {
        return authorService.removeById(id);
    }
}

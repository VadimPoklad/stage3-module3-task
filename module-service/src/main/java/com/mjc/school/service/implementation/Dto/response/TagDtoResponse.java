package com.mjc.school.service.implementation.Dto.response;

public record TagDtoResponse(
        String id,
        String name
) implements Response {
    @Override
    public String toString() {
        return "Tag{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
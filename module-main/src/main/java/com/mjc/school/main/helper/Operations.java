package com.mjc.school.main.helper;


import lombok.Getter;

import static com.mjc.school.main.helper.Constant.OPERATION;

public enum Operations {
    GET_ALL_NEWS(1, "Get all news."),
    GET_NEWS_BY_ID(2, "Get news by id."),
    GET_NEWS_BY_AUTHOR_NAME(3, "Get news by author name."),
    GET_NEWS_BY_TITLE(4, "Get news by title."),
    GET_NEWS_BY_CONTENT(5, "Get news by content."),
    GET_NEWS_BY_TAG_IDS(6, "Get news by tag ids."),
    GET_NEWS_BY_TAG_NAMES(7, "Get news by tag names."),
    CREATE_NEWS(8, "Create news."),
    UPDATE_NEWS(9, "Update news."),
    REMOVE_NEWS_BY_ID(10, "Remove news by id."),

    GET_ALL_AUTHORS(11, "Get all authors."),
    GET_AUTHOR_BY_ID(12, "Get author by id."),
    CREATE_AUTHOR(13, "Create author."),
    UPDATE_AUTHOR(14, "Update author."),
    REMOVE_AUTHOR_BY_ID(15, "Remove author by id."),

    GET_ALL_TAGS(16, "Get all tags."),
    GET_TAG_BY_ID(17, "Get tag by id."),
    CREATE_TAG(18, "Create tag."),
    UPDATE_TAG(19, "Update tag."),
    REMOVE_TAG_BY_ID(20, "Remove tag by id."),

    EXIT(0, "Exit."),
    ;

    @Getter
    private final int operationNumber;
    private final String operation;

    Operations(int operationNumber, String operation) {
        this.operationNumber = operationNumber;
        this.operation = operation;
    }

    public String getOperation() {
        return OPERATION + operation;
    }

    public String getOperationWithNumber() {
        return operationNumber + " - " + operation;
    }

}

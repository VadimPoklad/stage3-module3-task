package com.mjc.school.main.helper;

import java.util.Map;

import static com.mjc.school.main.helper.Operations.*;


public record Command(
    int operation,
    Map<String, String> params,
    String body) {

    public static Command NOT_FOUND = new Command(-1, null, null);
    public static Command GET_NEWS = new Command(GET_ALL_NEWS.getOperationNumber(), null, null);
    public static Command GET_AUTHORS = new Command(GET_ALL_AUTHORS.getOperationNumber(), null, null);
    public static Command GET_TAGS = new Command(GET_ALL_TAGS.getOperationNumber(), null, null);
}
